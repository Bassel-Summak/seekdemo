package com.basapps.seekdemo.job.domain

import com.basapps.seekdemo.job.data.AuthRepository
import com.basapps.seekdemo.job.data.UserDataRequest
import com.basapps.seekdemo.common.domain.mappers.UserMapper
import com.basapps.seekdemo.common.domain.models.Resource
import com.basapps.seekdemo.common.domain.models.User
import com.basapps.seekdemo.common.domain.toResourceError
import com.basapps.seekdemo.network.NetworkResult
import com.basapps.seekdemo.storage.SessionHandler
import javax.inject.Inject

class UserDataUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val mapper: UserMapper,
    private val sessionHandler: SessionHandler
) {

    suspend fun invoke(): Resource<User> {

        val request  = UserDataRequest()

        return when (val result = repository.user(request)) {
            is NetworkResult.Error -> result.toResourceError()
            is NetworkResult.Success ->{
                sessionHandler.setCurrentUser(
                    id = result.result.data.authuser.id, authKey = null,
                    name = result.result.data.authuser.displayname, username =  result.result.data.authuser.username, img = "https://avatar.iran.liara.run/public/boy?username=Ash")
                Resource.Success(mapper.map(result.result.data.authuser))
            }
        }
    }
}