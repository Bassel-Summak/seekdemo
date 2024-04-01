package com.basapps.seekdemo.common.data.models
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName



@Serializable
data class JobCollectionApiModel(
    @SerialName("active")
    val active: Active
) {
    @Serializable
    data class Active(
        @SerialName("hasNext")
        val hasNext: Boolean,
        @SerialName("jobs")
        val jobs: List<JobDetailsApiModel.Job>,
        @SerialName("page")
        val page: Int,
        @SerialName("size")
        val size: Int,
        @SerialName("total")
        val total: Int
    )
}