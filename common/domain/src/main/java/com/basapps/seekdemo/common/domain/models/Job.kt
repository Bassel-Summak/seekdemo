package com.basapps.seekdemo.common.domain.models


data class Job(
    val id: String,
    val description: String,
    val haveIApplied: Boolean,
    val industry: Int,
    val location: Int,
    val positionTitle: String,
    val maxSalary : String,
    val minSalary: String
)
