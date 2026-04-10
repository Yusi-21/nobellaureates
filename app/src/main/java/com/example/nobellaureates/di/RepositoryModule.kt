package com.example.nobellaureates.di

import com.example.nobellaureates.data.repository.AuthRepositoryImpl
import com.example.nobellaureates.data.repository.FavoritesRepositoryImpl
import com.example.nobellaureates.data.repository.LaureateRepositoryImpl
import com.example.nobellaureates.domain.repository.AuthRepository
import com.example.nobellaureates.domain.repository.FavoritesRepository
import com.example.nobellaureates.domain.repository.LaureateRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindLaureateRepository(
        impl: LaureateRepositoryImpl
    ): LaureateRepository

    @Binds
    @Singleton
    abstract fun bindFavoritesRepository(
        impl: FavoritesRepositoryImpl
    ): FavoritesRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository
}