package com.basapps.seekdemo.job.domain

import com.basapps.seekdemo.job.data.AuthRepository
import com.basapps.seekdemo.job.data.UserLoginRequest
import com.basapps.seekdemo.job.data.Variables
import com.basapps.seekdemo.common.domain.mappers.UserMapper
import com.basapps.seekdemo.common.domain.models.Resource
import com.basapps.seekdemo.common.domain.models.User
import com.basapps.seekdemo.common.domain.toResourceError
import com.basapps.seekdemo.network.NetworkResult
import com.basapps.seekdemo.storage.SessionHandler
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val sessionHandler: SessionHandler,
    private val mapper: UserMapper,
) {

    suspend fun invoke(email: String, password: String): Resource<User> {
        val request = UserLoginRequest(variables = Variables(username = email, password = password))
        return when (val result = repository.login(request)) {
            is NetworkResult.Error -> {
                result.toResourceError()
            }

            is NetworkResult.Success -> {
                println("ggggggggg " + result.result.data.authuser)
                sessionHandler.setCurrentUser(
                    id = result.result.data.authuser.id, authKey = result.result.data.authuser.token,
                    name = result.result.data.authuser.displayname, username =  result.result.data.authuser.username, img = "https://avatar.iran.liara.run/public/boy?username=Ash")
                Resource.Success(mapper.map(result.result.data.authuser))
            }
        }
    }
}
