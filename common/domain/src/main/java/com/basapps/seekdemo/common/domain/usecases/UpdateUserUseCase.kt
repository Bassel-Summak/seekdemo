package com.basapps.seekdemo.common.domain.usecases

import com.basapps.seekdemo.common.data.models.UserUpdateDataRequest
import com.basapps.seekdemo.common.data.repositories.UserRepository
import com.basapps.seekdemo.common.domain.mappers.UserUpdateMapper
import com.basapps.seekdemo.common.domain.models.Resource
import com.basapps.seekdemo.common.domain.models.Result
import com.basapps.seekdemo.common.domain.toResourceError
import com.basapps.seekdemo.network.NetworkResult
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val repository: UserRepository,
    private val mapper: UserUpdateMapper,
) {
    suspend fun invoke(name:String): Resource<Result> {

        val request  = UserUpdateDataRequest(variables = UserUpdateDataRequest.Variables(name = name))

        return when (val result = repository.updaterUser(request)) {
            is NetworkResult.Error -> result.toResourceError()
            is NetworkResult.Success -> Resource.Success(mapper.map(result.result.data))
        }
    }
}