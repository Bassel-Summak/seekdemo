package com.basapps.seekdemo.job.data

import com.basapps.seekdemo.common.data.models.JobApplyApiModel
import com.basapps.seekdemo.common.data.models.JobCollectionApiModel
import com.basapps.seekdemo.common.data.models.JobDetailsApiModel
import com.basapps.seekdemo.network.NetworkResult
import com.basapps.seekdemo.network.Response

class JobRepositoryImpl @javax.inject.Inject constructor(
    private val requestHandler: com.basapps.seekdemo.network.http.RequestHandler,
) : JobRepository {

    override suspend fun getJobById(request: JobDetailsRequest): NetworkResult<Response<JobDetailsApiModel>> =
         requestHandler.post(
            urlPathSegments = listOf(""),
            body = request,
        )

    override suspend fun getJobCollections(request: JobCollectionRequest): NetworkResult<Response<JobCollectionApiModel>> =
        requestHandler.post(
             urlPathSegments = listOf(""),
             body = request,
         )

    override suspend fun applyOfJob(request: JobApplyRequest): NetworkResult<Response<JobApplyApiModel>> =
        requestHandler.post(
            urlPathSegments = listOf(""),
            body = request,
        )
}