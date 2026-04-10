package com.example.nobellaureates.domain.repository

interface AuthRepository {
    suspend fun login(username: String, password: String): String
}