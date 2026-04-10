package com.example.nobellaureates.data.repository

import com.example.nobellaureates.data.remote.AuthApiService
import com.example.nobellaureates.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService
) : AuthRepository {
    override suspend fun login(username: String, password: String): String {
        return authApiService.login(username, password)
    }
}