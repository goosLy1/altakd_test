package com.example.altakidtest.data.repository

import io.github.jan.supabase.gotrue.user.UserInfo

interface AuthenticationRepository {
    suspend fun signIn(email: String, password: String): Boolean
    suspend fun signUp(email: String, password: String): UserInfo?
    suspend fun signInWithGoogle(): Boolean
    suspend fun signOut(): Boolean
    suspend fun getCurrentUser(): UserInfo?
    fun hasUser(): Boolean
    suspend fun resetPassword(email: String)
}