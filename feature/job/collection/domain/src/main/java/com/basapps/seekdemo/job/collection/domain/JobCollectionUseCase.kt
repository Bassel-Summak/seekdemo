package com.basapps.seekdemo.job.collection.domain

import com.basapps.seekdemo.common.domain.mappers.JobCollectionsMapper
import com.basapps.seekdemo.common.domain.models.JobsCollection
import com.basapps.seekdemo.common.domain.models.Resource
import com.basapps.seekdemo.common.domain.toResourceError
import com.basapps.seekdemo.job.data.JobCollectionRequest
import com.basapps.seekdemo.job.data.JobRepository
import com.basapps.seekdemo.network.NetworkResult
import javax.inject.Inject


class JobCollectionUseCase @Inject constructor(
    private val repository: JobRepository,
    private val mapper: JobCollectionsMapper,
) {

    suspend fun invoke(limit: Int, page: Int,positionTitle:String = "", appliedOnly:Boolean = false): Resource<JobsCollection> {
        val request = JobCollectionRequest(variables = JobCollectionRequest.Variables(
            limit = limit,
            page = page,
            positionTitle = positionTitle,
            appliedonly = appliedOnly
        ))

        return when (val result = repository.getJobCollections(request)) {
            is NetworkResult.Error -> {
                result.toResourceError()
            }

            is NetworkResult.Success -> {
                Resource.Success(mapper.map(result.result.data))
            }
        }
    }
}
