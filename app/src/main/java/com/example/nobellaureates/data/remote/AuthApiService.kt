package com.example.nobellaureates.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.Serializable
import javax.inject.Inject

@Serializable
data class LoginRequest(
    val username: String,
    val password: String
)

@Serializable
data class LoginResponse(
    val token: String
)

class AuthApiService @Inject constructor(
    private val client: HttpClient
) {
    suspend fun login(username: String, password: String): String {
        val response = client.post("/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(username, password))
        }
        return response.body<LoginResponse>().token
    }
}