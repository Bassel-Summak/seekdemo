package com.basapps.seekdemo.common.data.models
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName
import javax.xml.crypto.Data



    @Serializable
    data class UserUpdateApiModel(
        @SerialName("updateuser")
        val updateuser: Boolean
    )
