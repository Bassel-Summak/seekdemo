package com.basapps.seekdemo.common.data.models
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


    @Serializable
    data class JobDetailsApiModel(
        @SerialName("job")
        val job: Job
    ) {
        @Serializable
        data class Job(
            @SerialName("_id")
            val id: String,
            @SerialName("positionTitle")
            val positionTitle: String,
            @SerialName("description")
            val description: String,
            @SerialName("industry")
            val industry: Int,
            @SerialName("location")
            val location: Int,
            @SerialName("salaryRange")
            val salaryRange: SalaryRange,
            @SerialName("haveIApplied")
            val haveIApplied: Boolean,
        ) {
            @Serializable
            data class SalaryRange(
                @SerialName("max")
                val max: Int,
                @SerialName("min")
                val min: Int
            )
        }
    }
