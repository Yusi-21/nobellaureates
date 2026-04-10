package com.example.nobellaureates.domain.usecase

import com.example.nobellaureates.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String): Result<String> {
        return try {
            val token = authRepository.login(username, password)
            Result.success(token)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}