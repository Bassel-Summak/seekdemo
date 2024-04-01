package com.basapps.seekdemo.job.collection.presentation

import com.basapps.seekdemo.common.domain.models.JobsCollection

data class JobCollectionUiState (

    val jobListStatus: JobListStatus = JobListStatus.Loading,
    val isListRequested: Boolean = false,
    val tabUI : Tab = Tab.ActiveJobs
)

sealed class JobListStatus {
    data object Loading : JobListStatus()
    data object Empty : JobListStatus()
    data object Retrying : JobListStatus()
    data class ShowData(val jobsList: JobsCollection) : JobListStatus()
    data class AddData(val newJobsList: JobsCollection) : JobListStatus()
}


sealed class Tab {
    data object  ActiveJobs : Tab()
    data object  Applied : Tab()
}