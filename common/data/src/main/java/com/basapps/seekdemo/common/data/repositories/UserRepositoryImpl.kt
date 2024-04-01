package com.basapps.seekdemo.common.data.repositories

import com.basapps.seekdemo.common.data.models.UserUpdateApiModel
import com.basapps.seekdemo.common.data.models.UserUpdateDataRequest
import com.basapps.seekdemo.network.NetworkResult
import com.basapps.seekdemo.network.Response
import com.basapps.seekdemo.network.http.RequestHandler
import javax.inject.Inject


private const val ROUTE = ""

class UserRepositoryImpl @Inject constructor(
    private val requestHandler: RequestHandler,
) : UserRepository {

    override suspend fun updaterUser(request: UserUpdateDataRequest): NetworkResult<Response<UserUpdateApiModel>> =
        requestHandler.post(
        urlPathSegments = listOf(ROUTE),
        body = request,
    )
}
