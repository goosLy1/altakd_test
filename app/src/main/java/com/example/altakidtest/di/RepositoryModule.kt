package com.example.altakidtest.di

import com.example.altakidtest.data.repository.AuthenticationRepository
import com.example.altakidtest.data.repository.UserProfileRepository
import com.example.altakidtest.data.repository.impl.AuthenticationRepositoryImpl
import com.example.altakidtest.data.repository.impl.UserProfileRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthenticationRepository(impl: AuthenticationRepositoryImpl): AuthenticationRepository

    @Binds
    @Singleton
    abstract fun bindUserProfileRepository(impl: UserProfileRepositoryImpl): UserProfileRepository
}