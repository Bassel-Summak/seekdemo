package com.basapps.seekdemo.job.detail.presentation

import com.basapps.seekdemo.common.domain.models.Job
import com.basapps.seekdemo.components.UnidirectionalViewModel

interface JobDetailsContract :
    UnidirectionalViewModel<JobDetailsContract.State, JobDetailsContract.Event,
            JobDetailsContract.Effect> {

    data class State(
        val isAddJobButtonLoading: Boolean = false,
        val jobDetailsStatus: JobDetailsStatus = JobDetailsStatus.Loading,
    )



    sealed class Event {
        data class applyForJob(val jobId: String) : Event()
        data class getJobDetails(val jobId: String) : Event()
    }

    sealed class Effect {
        data class ShowToastMessageString (val message: String) : Effect()
        data object JobIsApplied  : Effect()
        data object GetJobDetails  : Effect()

    }


    sealed class JobDetailsStatus {
        data object Loading : JobDetailsStatus()
        data object Retrying : JobDetailsStatus()
        data class ShowData(val job: Job) : JobDetailsStatus()
    }



}
