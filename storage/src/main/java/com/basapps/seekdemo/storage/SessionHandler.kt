package com.basapps.seekdemo.storage

import kotlinx.coroutines.flow.Flow

interface SessionHandler {
    suspend fun setCurrentUser(id: String, authKey: String?,name:String,img:String,username:String)
    fun getCurrentUser(): Flow<CurrentUser>
    suspend fun clear()
}
