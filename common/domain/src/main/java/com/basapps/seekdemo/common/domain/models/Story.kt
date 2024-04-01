package com.basapps.seekdemo.common.domain.models

data class Story(
    val id: Int,
    val title: String,
    val content: String,
    val createdAt: String,
    val isDraft: Boolean,
)
