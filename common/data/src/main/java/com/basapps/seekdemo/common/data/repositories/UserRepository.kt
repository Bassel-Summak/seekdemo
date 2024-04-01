package com.basapps.seekdemo.common.data.repositories

import com.basapps.seekdemo.common.data.models.UserUpdateApiModel
import com.basapps.seekdemo.common.data.models.UserUpdateDataRequest
import com.basapps.seekdemo.network.NetworkResult
import com.basapps.seekdemo.network.Response


interface UserRepository {
    suspend fun updaterUser(request: UserUpdateDataRequest): NetworkResult<Response<UserUpdateApiModel>>

}
