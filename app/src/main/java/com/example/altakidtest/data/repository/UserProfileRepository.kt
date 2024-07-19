package com.example.altakidtest.data.repository

import com.example.altakidtest.data.dto.ProfileDto

interface UserProfileRepository {
    suspend fun createUserProfile(profile: ProfileDto)
}