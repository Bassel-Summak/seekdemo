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

const val C_JOBID = "job-5"

@RunWith(JUnit4::class)
class JobRepositoryImplTest {

    private lateinit var repository: JobRepositoryImpl

    @Before
    fun initRepository() {
        val httpClient = ApiMockEngine()
        val requestHandler = RequestHandler(httpClient.client)
        repository = JobRepositoryImpl(requestHandler)
    }

    @Test
    fun `job should succeed with valid data`(): Unit = runBlocking {
        // Given
        val validUserCredentials = JobDetailsRequest( variables = JobDetailsRequest.Variables(jobId = C_JOBID))

        // When
        val result = repository.getJobById(validUserCredentials)

        // Then
        assertWithMessage("Expected successful login result, but was ${result::class.simpleName}")
            .that(result)
            .instanceOf(NetworkResult.Success::class)



        val jobData = (result as NetworkResult.Success).result.data
        with(jobData) {
            assertWithMessage("Job Description should not be null").that(job.description).isNotNull()
            assertWithMessage("User id should be 1").that(job.id).isEqualTo(C_JOBID)
            assertWithMessage("Job PositionTitle should not be empty").that(job.positionTitle).isNotEmpty()
        }

    }

    @Test
    fun `job should fail with invalid credentials`(): Unit = runBlocking {
        // Given
        val validUserCredentials = JobDetailsRequest( variables = JobDetailsRequest.Variables(jobId = "job5"))

        // When
        val result = repository.getJobById(validUserCredentials)

        // Then
        assertWithMessage("Expected error result, but was ${result::class.simpleName}")
            .that(result)
            .instanceOf(NetworkResult.Error::class)
    }
}
