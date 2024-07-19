package com.example.altakidtest.di

import com.example.altakidtest.domain.usecase.SignInUseCase
import com.example.altakidtest.domain.usecase.impl.SignInUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindSignInUseCase(impl: SignInUseCaseImpl): SignInUseCase

//    @Binds
//    @Singleton
//    abstract fun bindSignUpUseCase(impl: SignUpUseCaseImpl): SignUpUseCase
}