package com.example.nobellaureates.di

import com.example.nobellaureates.presentation.common.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.internal.connection.ConnectInterceptor.intercept
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
// used for practice-2
//    @Provides
//    @Singleton
//    fun provideHttpClient(): HttpClient {
//        return HttpClient(Android) {
//            install(ContentNegotiation) {
//                json(Json {
//                    ignoreUnknownKeys = true
//                    isLenient = true
//                })
//            }
//        }
//    }

    @Provides
    @Singleton
    fun provideHttpClient(
        tokenManager: TokenManager
    ): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
            install(DefaultRequest) {
                url("http://192.168.31.116:8080")
                tokenManager.getToken()?.let {
                    header("Authorization", "Bearer $it")
                }
            }
        }
    }
}