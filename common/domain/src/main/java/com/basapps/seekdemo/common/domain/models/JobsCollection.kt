package com.basapps.seekdemo.common.domain.models


data class JobsCollection(
    val jobs : ArrayList<Job>,
    var isThereMorePages :Boolean,
    var page: Int,
    var size: Int,
    var total: Int,
    var query: String = "",
    var isAppliedOnly: Boolean = false
)
