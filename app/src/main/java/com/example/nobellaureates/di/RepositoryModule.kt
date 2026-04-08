package com.example.nobellaureates.di

import com.example.nobellaureates.data.repository.LaureateRepositoryImpl
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
}