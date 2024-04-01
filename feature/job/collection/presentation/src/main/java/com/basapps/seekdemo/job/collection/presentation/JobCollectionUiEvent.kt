package com.basapps.seekdemo.job.collection.presentation

import com.basapps.seekdemo.common.domain.models.Job

sealed class JobCollectionUiEvent {
    object  GetJobCollection : JobCollectionUiEvent()
    object  GetAppliedJobs : JobCollectionUiEvent()
    data class GetNextJobCollectionPage(val page: Int) : JobCollectionUiEvent()
    data object  RetryLoadingList : JobCollectionUiEvent()
}
