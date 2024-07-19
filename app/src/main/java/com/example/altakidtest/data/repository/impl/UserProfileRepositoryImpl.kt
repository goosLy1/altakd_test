package com.example.altakidtest.data.repository.impl

import com.example.altakidtest.data.dto.ProfileDto
import com.example.altakidtest.data.repository.UserProfileRepository
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Inject

class UserProfileRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest
): UserProfileRepository {
    override suspend fun createUserProfile(profile: ProfileDto) {
        postgrest["profile"].insert(profile)
    }

}