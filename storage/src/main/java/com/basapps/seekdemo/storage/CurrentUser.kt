package com.basapps.seekdemo.storage

data class CurrentUser(
    val id: String,
    val authKey: String,
    var name: String,
    val img: String,
    val username: String,
)
