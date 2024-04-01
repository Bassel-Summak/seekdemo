package com.basapps.seekdemo.common.domain

import com.basapps.seekdemo.common.domain.models.Resource
import com.basapps.seekdemo.common.domain.models.ResourceError
import com.basapps.seekdemo.network.NetworkException
import com.basapps.seekdemo.network.NetworkResult

fun NetworkResult.Error<*>.toResourceError(): Resource.Error {
    return when (exception) {
        is NetworkException.NotFoundException -> Resource.Error(ResourceError.SERVICE_UNAVAILABLE)
        is NetworkException.UnauthorizedException -> Resource.Error(ResourceError.UNAUTHORIZED)
        is NetworkException.UnknownException -> Resource.Error(ResourceError.UNKNOWN)
    }
}
