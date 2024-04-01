package com.basapps.seekdemo.job.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basapps.seekdemo.common.domain.models.Resource
import com.basapps.seekdemo.common.domain.models.ResourceError
import com.basapps.seekdemo.job.detail.domain.ApplyForJobUseCase
import com.basapps.seekdemo.job.detail.domain.JobDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobDetailsViewModel @Inject constructor(
    private val jobDetailsUseCase: JobDetailsUseCase,
    private val applyForJobUseCase: ApplyForJobUseCase,
) : ViewModel(),JobDetailsContract {

    private val _state = MutableStateFlow(JobDetailsContract.State())
    override val state: StateFlow<JobDetailsContract.State> = _state.asStateFlow()

    private val _effectFlow = MutableSharedFlow<JobDetailsContract.Effect>()
    override val effect: SharedFlow<JobDetailsContract.Effect> = _effectFlow.asSharedFlow()


    override fun onEvent(uiEvent: JobDetailsContract.Event) {
        when (uiEvent) {
            is JobDetailsContract.Event.applyForJob -> applyJobById(uiEvent.jobId)
            is JobDetailsContract.Event.getJobDetails -> getJobDetails(uiEvent.jobId)

        }
    }

    init {
        viewModelScope.launch {
            delay(50)
            _effectFlow.emit(
                JobDetailsContract.Effect.GetJobDetails
            )
        }
    }


    private fun getJobDetails(jobId:String) = viewModelScope.launch {

        _state.update { it.copy(jobDetailsStatus = JobDetailsContract.JobDetailsStatus.Loading) }

        when (val jobDetailsUseCase = jobDetailsUseCase.invoke(jobId)) {
            is Resource.Success -> {
                _state.update { it.copy(jobDetailsStatus = JobDetailsContract.JobDetailsStatus.ShowData(jobDetailsUseCase.result)) }
            }
            is Resource.Error -> {
                _state.update { it.copy(jobDetailsStatus = JobDetailsContract.JobDetailsStatus.Retrying) }
            }
        }
    }

    private fun applyJobById(jobId:String) = viewModelScope.launch {
        _state.update { it.copy(isAddJobButtonLoading = true) }

        when (val applyForJobUseCase = applyForJobUseCase.invoke(jobId)) {
            is Resource.Success -> {
                _state.update { it.copy(isAddJobButtonLoading = false) }
                showMessage("Job has been applied successfully")
                JobIsApplied()
            }
            is Resource.Error -> {
                _state.update { it.copy(isAddJobButtonLoading = false, ) }
                showMessage(getError(applyForJobUseCase))
            }
        }
    }

    private fun showMessage(string: String){
        viewModelScope.launch {
            _effectFlow.emit(
                JobDetailsContract.Effect.ShowToastMessageString(string)
            )
        }
    }

    private fun JobIsApplied(){
        viewModelScope.launch {
            _effectFlow.emit(
                JobDetailsContract.Effect.JobIsApplied
            )
        }
    }



    private fun getError(loginError: Resource.Error): String {
        return when (loginError.e) {
            ResourceError.UNAUTHORIZED -> "Session is expired"
            ResourceError.SERVICE_UNAVAILABLE -> "Service not available"
            ResourceError.UNKNOWN ->  "Unable to apply for the job"
        }
    }
}
