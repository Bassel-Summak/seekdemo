package com.basapps.seekdemo.job.data

import com.basapps.seekdemo.common.data.models.UserApiModel
import com.basapps.seekdemo.common.data.models.UserInfoModel
import com.basapps.seekdemo.network.NetworkResult
import com.basapps.seekdemo.network.Response
import com.basapps.seekdemo.network.http.RequestHandler
import javax.inject.Inject


private const val LOGIN = ""

class AuthRepositoryImpl @Inject constructor(
    private val requestHandler: RequestHandler,
) : AuthRepository {

    override suspend fun login(request: UserLoginRequest): NetworkResult<Response<UserApiModel>> =
        requestHandler.post(
            urlPathSegments = listOf(LOGIN),
            body = request,
        )

    override suspend fun user(request: UserDataRequest): NetworkResult<Response<UserInfoModel>> =
        requestHandler.post(
            urlPathSegments = listOf(LOGIN),
            body = request,
        )
}
