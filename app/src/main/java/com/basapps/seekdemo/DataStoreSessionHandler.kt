package com.basapps.seekdemo

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.basapps.seekdemo.storage.CurrentUser
import com.basapps.seekdemo.storage.User
import com.basapps.seekdemo.storage.SessionHandler
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlinx.coroutines.flow.map

private val Context.userDataStore: DataStore<User> by dataStore(
    fileName = "user.pb",
    serializer = UserSerializer,
)

class DataStoreSessionHandler @Inject constructor(
    @ApplicationContext private val context: Context,
) : SessionHandler {


    override suspend fun setCurrentUser(id: String, authKey: String?,name:String,img:String,username:String) {
        context.userDataStore.updateData {
            if (authKey == null)
            it.toBuilder()
                .setId(id)
                .setImg(img)
                .setUsername(username)
                .setName(name)
                .build()
            else
                it.toBuilder()
                    .setAuthKey(authKey)
                    .setId(id)
                    .setImg(img)
                    .setUsername(username)
                    .setName(name)
                    .build()
        }
    }

    override fun getCurrentUser(): Flow<CurrentUser> {
        return context.userDataStore.data.map {
            CurrentUser(id = it.id, authKey =  it.authKey,name = it.name, img = it.img, username = it.username )
        }
    }

    override suspend fun clear() {
        context.userDataStore.updateData {
            it.toBuilder().clear().build()

            /*
            it.toBuilder()
                .setAuthKey("")
                .setId()
                .build()
            */

        }
    }
}