package com.basapps.seekdemo.job.collection.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basapps.seekdemo.common.domain.models.Resource
import com.basapps.seekdemo.common.domain.models.ResourceError
import com.basapps.seekdemo.job.collection.domain.JobCollectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobCollectionViewModel @Inject constructor(
    private val jobCollectionUseCase: JobCollectionUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<JobCollectionUiState>(JobCollectionUiState(jobListStatus = JobListStatus.Loading))
    val uiState: StateFlow<JobCollectionUiState> = _uiState

    fun onEvent(uiEvent: JobCollectionUiEvent) {
        when (uiEvent) {
            is JobCollectionUiEvent.GetNextJobCollectionPage -> {
                getNextJobList(uiEvent.page)
            }
            is JobCollectionUiEvent.GetJobCollection -> {
                viewModelScope.coroutineContext.cancelChildren()
                getJobList(false)
                _uiState.update { it.copy(tabUI =Tab.ActiveJobs, jobListStatus = JobListStatus.Loading) }
            }
            is JobCollectionUiEvent.RetryLoadingList -> getJobList(_uiState.value.tabUI == Tab.Applied)
            is JobCollectionUiEvent.GetAppliedJobs -> {
                viewModelScope.coroutineContext.cancelChildren()
                getJobList(true)
                _uiState.update { it.copy(tabUI =Tab.Applied, jobListStatus = JobListStatus.Loading) }
            }

            is JobCollectionUiEvent.GetQueryJobs -> {
                viewModelScope.coroutineContext.cancelChildren()
                getJobListQuery(uiEvent.data)
                _uiState.update { it.copy(tabUI =Tab.Search, jobListStatus = JobListStatus.Loading) }
            }
            is JobCollectionUiEvent.ShowSearchUI -> {
                viewModelScope.coroutineContext.cancelChildren()
                _uiState.update { it.copy(tabUI =Tab.Search,jobListStatus = JobListStatus.None) }
            }
        }
    }

    init {
        onEvent(JobCollectionUiEvent.GetJobCollection)
    }



    private fun getJobList(appliedOnly:Boolean) = viewModelScope.launch {

        _uiState.update { it.copy(isListRequested = true) }

        when (val loginResult = jobCollectionUseCase.invoke(5, 1,appliedOnly = appliedOnly)) {
            is Resource.Success -> {
                if (loginResult.result.jobs.isEmpty())
                    _uiState.update { it.copy(jobListStatus = JobListStatus.Empty) }
                else
                _uiState.update { it.copy(jobListStatus = JobListStatus.ShowData(loginResult.result)) }

                enableListRequests()
            }
            is Resource.Error -> {
                _uiState.update { it.copy(jobListStatus = JobListStatus.Retrying) }
                enableListRequests()

            }
        }
    }

    private fun getJobListQuery(query:String) = viewModelScope.launch {

        _uiState.update { it.copy(isListRequested = true) }

        when (val loginResult = jobCollectionUseCase.invoke(5, 1, positionTitle = query)) {
            is Resource.Success -> {
                if (loginResult.result.jobs.isEmpty())
                    _uiState.update { it.copy(jobListStatus = JobListStatus.Empty) }
                else
                    _uiState.update { it.copy(jobListStatus = JobListStatus.ShowData(loginResult.result)) }

                enableListRequests()
            }
            is Resource.Error -> {
                _uiState.update { it.copy(jobListStatus = JobListStatus.Retrying) }
                enableListRequests()

            }
        }
    }


    private fun enableListRequests(){
        _uiState.update { it.copy(isListRequested = false) }

    }

    private fun getNextJobList(pageNum: Int) = viewModelScope.launch {

        _uiState.update { it.copy(isListRequested = true) }

        var appliedOnly = false
        var positionTitle = ""
        when (val data = uiState.value.jobListStatus){
            is JobListStatus.ShowData ->{
                positionTitle = data.jobsList.query
                appliedOnly = data.jobsList.isAppliedOnly
            }
            else ->{

            }
        }

        when (val loginResult = jobCollectionUseCase.invoke(5, pageNum, appliedOnly = appliedOnly, positionTitle = positionTitle)) {
            is Resource.Success -> {


                when (val listStatus = uiState.value.jobListStatus){
                    is JobListStatus.ShowData ->{
                       // val currentList = listStatus.jobsList
                        listStatus.jobsList.isThereMorePages = loginResult.result.isThereMorePages && loginResult.result.jobs.isNotEmpty()
                        listStatus.jobsList.page = loginResult.result.page
                        listStatus.jobsList.jobs.addAll(loginResult.result.jobs)
                        _uiState.update { it.copy(isListRequested = false) }
                        enableListRequests()
                    }
                    else ->{
                        _uiState.update { it.copy(isListRequested = false,jobListStatus = JobListStatus.ShowData(loginResult.result)) }
                        enableListRequests()
                    }

                }


            }
            is Resource.Error -> {
                enableListRequests()
                _uiState.update { it.copy(jobListStatus = JobListStatus.Retrying) }

            }
        }
    }


    private fun getError(loginError: Resource.Error): String {
        return when (loginError.e) {
            ResourceError.UNAUTHORIZED -> "Session is expired"
            ResourceError.SERVICE_UNAVAILABLE -> "Service not available"
            ResourceError.UNKNOWN ->  "Unknown error"
        }
    }
}
