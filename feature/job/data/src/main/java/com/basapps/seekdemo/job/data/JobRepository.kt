package com.basapps.seekdemo.job.data

import com.basapps.seekdemo.common.data.models.JobApplyApiModel
import com.basapps.seekdemo.common.data.models.JobCollectionApiModel
import com.basapps.seekdemo.common.data.models.JobDetailsApiModel
import com.basapps.seekdemo.network.NetworkResult
import com.basapps.seekdemo.network.Response


interface JobRepository {
    suspend fun getJobById(request: JobDetailsRequest): NetworkResult<Response<JobDetailsApiModel>>
    suspend fun getJobCollections(request: JobCollectionRequest): NetworkResult<Response<JobCollectionApiModel>>
    suspend fun applyOfJob(request: JobApplyRequest): NetworkResult<Response<JobApplyApiModel>>
}
