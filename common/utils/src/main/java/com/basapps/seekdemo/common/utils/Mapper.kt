package com.basapps.seekdemo.common.utils

interface Mapper<F, T> {
    fun map(from: F): T
}
