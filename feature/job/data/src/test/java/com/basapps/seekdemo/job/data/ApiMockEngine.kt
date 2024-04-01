package com.basapps.seekdemo.job.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.toByteArray
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ApiMockEngine {

    private val responseHeaders = headersOf("Content-Type", "application/json")
    val client = HttpClient(MockEngine) {

        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                },
            )
        }

        defaultRequest {
            header(HttpHeaders.ContentType, "application/json")
        }



        engine {
            addHandler {
                request ->
                val reqBody = request.body.toByteArray().toString(Charsets.UTF_8)
                val req = Json.decodeFromString<JobDetailsRequest>(reqBody)
                if (req.variables.jobId == C_JOBID) {
                    respond(loginSuccess, HttpStatusCode.OK, responseHeaders)
                } else {
                    respond(loginFailure, HttpStatusCode.OK, responseHeaders)
                }


            }
        }
    }
}
