package com.basapps.seekdemo.job.data

import com.basapps.seekdemo.common.data.models.UserApiModel
import com.basapps.seekdemo.common.data.models.UserInfoModel
import com.basapps.seekdemo.network.NetworkResult
import com.basapps.seekdemo.network.Response


interface AuthRepository {
    suspend fun login(request: UserLoginRequest): NetworkResult<Response<UserApiModel>>

    suspend fun user(request: UserDataRequest): NetworkResult<Response<UserInfoModel>>

}
