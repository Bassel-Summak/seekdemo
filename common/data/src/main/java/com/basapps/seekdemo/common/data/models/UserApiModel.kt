package com.basapps.seekdemo.common.data.models
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


@Serializable
data class UserApiModel(
    @SerialName("authuser")
    val authuser: Authuser
) {
        @Serializable
        data class Authuser(
            @SerialName("displayname")
            val displayname: String,
            @SerialName("_id")
            val id: String,
            @SerialName("token")
            val token: String?,
            @SerialName("username")
            val username: String
        )

}