package com.basapps.seekdemo.job.data

import com.basapps.seekdemo.network.NetworkResult
import com.basapps.seekdemo.network.http.RequestHandler
import com.google.common.truth.Truth.assertWithMessage
import io.ktor.util.reflect.instanceOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

const val C_USERNAME = "user1"
const val C_PASSWORD = "Seeker1123"

@RunWith(JUnit4::class)
class AuthRepositoryImplTest {

    private lateinit var repository: AuthRepositoryImpl

    @Before
    fun initRepository() {
        val httpClient = ApiMockEngine()
        val requestHandler = RequestHandler(httpClient.client)
        repository = AuthRepositoryImpl(requestHandler)
    }

    @Test
    fun `login should succeed with valid credentials`(): Unit = runBlocking {
        // Given
        val validUserCredentials = UserLoginRequest( variables = Variables(C_USERNAME,C_PASSWORD))

        // When
        val result = repository.login(validUserCredentials)

        println(result.toString())
        // Then
        assertWithMessage("Expected successful login result, but was ${result::class.simpleName}")
            .that(result)
            .instanceOf(NetworkResult.Success::class)



        val userData = (result as NetworkResult.Success).result.data
        with(userData) {
            assertWithMessage("Auth token should not be null").that(authuser.token).isNotNull()
            assertWithMessage("User id should be 1").that(authuser.id).isEqualTo("user-1")
            assertWithMessage("Username should not be empty").that(authuser.username).isNotEmpty()
            assertWithMessage("Full name should not be empty").that(authuser.displayname).isNotEmpty()
        }

    }

    @Test
    fun `login should fail with invalid credentials`(): Unit = runBlocking {
        // Given
        val validUserCredentials = UserLoginRequest( variables = Variables("user1","Seeker3323"))

        // When
        val result = repository.login(validUserCredentials)

        // Then
        assertWithMessage("Expected error result, but was ${result::class.simpleName}")
            .that(result)
            .instanceOf(NetworkResult.Error::class)
    }
}
