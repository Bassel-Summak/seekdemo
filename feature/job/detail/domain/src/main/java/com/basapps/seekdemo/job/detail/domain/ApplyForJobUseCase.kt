package com.basapps.seekdemo.job.detail.domain

import com.basapps.seekdemo.common.domain.mappers.JobApplyingMapper
import com.basapps.seekdemo.common.domain.models.Resource
import com.basapps.seekdemo.common.domain.models.Result
import com.basapps.seekdemo.common.domain.toResourceError
import com.basapps.seekdemo.job.data.JobApplyRequest
import com.basapps.seekdemo.job.data.JobRepository
import com.basapps.seekdemo.network.NetworkResult
import javax.inject.Inject


class ApplyForJobUseCase @Inject constructor(
    private val repository: JobRepository,
    private val mapper: JobApplyingMapper,
) {

    suspend fun invoke(jobId: String): Resource<Result> {
        val request = JobApplyRequest(variables = JobApplyRequest.Variables(
            jobId = jobId
        ))

        return when (val result = repository.applyOfJob(request)) {
            is NetworkResult.Error -> {
                result.toResourceError()
            }

            is NetworkResult.Success -> {
                Resource.Success(mapper.map(result.result.data))
            }
        }
    }
}
