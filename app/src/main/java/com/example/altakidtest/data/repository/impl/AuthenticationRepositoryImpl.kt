package com.example.altakidtest.data.repository.impl

import com.example.altakidtest.data.repository.AuthenticationRepository
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.providers.Google
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.user.UserInfo
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val auth: Auth
) : AuthenticationRepository {
    override suspend fun signIn(email: String, password: String): Boolean {
        return try {
            auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun signUp(email: String, password: String): UserInfo? {
        return try {
            auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }

        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun signInWithGoogle(): Boolean {
        return try {
            auth.signInWith(Google)
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun signOut(): Boolean {
        return try {
            auth.signOut()
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun getCurrentUser(): UserInfo? {
        auth.awaitInitialization()
        return auth.currentUserOrNull()
    }
//    override fun getCurrentUser(): UserInfo? {
//        return auth.currentUserOrNull()
//    }

    override fun hasUser(): Boolean {
        return auth.currentUserOrNull() != null
    }

    override suspend fun resetPassword(email: String) {
        auth.resetPasswordForEmail(email)
    }
}