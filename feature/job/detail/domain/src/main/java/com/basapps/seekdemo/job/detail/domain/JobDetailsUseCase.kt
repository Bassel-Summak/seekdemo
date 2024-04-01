package com.basapps.seekdemo.job.detail.domain

import com.basapps.seekdemo.common.domain.mappers.JobCollectionsMapper
import com.basapps.seekdemo.common.domain.mappers.JobDetailsMapper
import com.basapps.seekdemo.common.domain.models.Job
import com.basapps.seekdemo.common.domain.models.JobsCollection
import com.basapps.seekdemo.common.domain.models.Resource
import com.basapps.seekdemo.common.domain.models.User
import com.basapps.seekdemo.common.domain.toResourceError
import com.basapps.seekdemo.job.data.JobCollectionRequest
import com.basapps.seekdemo.job.data.JobDetailsRequest
import com.basapps.seekdemo.job.data.JobRepository
import com.basapps.seekdemo.network.NetworkResult
import com.basapps.seekdemo.storage.SessionHandler
import javax.inject.Inject


class JobDetailsUseCase @Inject constructor(
    private val repository: JobRepository,
    private val mapper: JobDetailsMapper,
) {

    suspend fun invoke(jobId: String): Resource<Job> {
        val request = JobDetailsRequest(variables = JobDetailsRequest.Variables(
            jobId = jobId
        ))

        return when (val result = repository.getJobById(request)) {
            is NetworkResult.Error -> {
                result.toResourceError()
            }

            is NetworkResult.Success -> {
                Resource.Success(mapper.map(result.result.data))
            }
        }
    }
}
