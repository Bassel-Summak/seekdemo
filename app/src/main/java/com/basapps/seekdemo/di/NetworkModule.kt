package com.basapps.seekdemo.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.basapps.seekdemo.BuildConfig
import com.basapps.seekdemo.DataStoreSessionHandler
import com.basapps.seekdemo.UserSerializer
import com.basapps.seekdemo.network.http.AppHttpClientBuilder
import com.basapps.seekdemo.network.http.RequestHandler
import com.basapps.seekdemo.storage.SessionHandler
import com.basapps.seekdemo.storage.User
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.http.URLProtocol

private val Context.userDataStore: DataStore<User> by dataStore(
    fileName = "user.pb",
    serializer = UserSerializer,
)

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<User> =
        context.userDataStore


    @Provides
    fun provideSessionHandler(dataStoreSessionHandler: DataStoreSessionHandler): SessionHandler =
        dataStoreSessionHandler


    @Provides
    fun provideHttpClient(sessionHandler: SessionHandler): HttpClient =
        AppHttpClientBuilder(sessionHandler)
            .protocol(URLProtocol.HTTP)
            .host(BuildConfig.HOSTURL)
            .port(3002)
            .build()

    @Provides
    fun provideRequestHandler(client: HttpClient) = RequestHandler(client)
}
