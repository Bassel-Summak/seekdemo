package com.basapps.seekdemo.common.data.models
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName



@Serializable
data class JobApplyApiModel(
    @SerialName("apply")
    val apply: Boolean
)