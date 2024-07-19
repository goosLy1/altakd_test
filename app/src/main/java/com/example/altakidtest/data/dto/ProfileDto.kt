package com.example.altakidtest.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileDto(
    @SerialName("user_id")
    val userId: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("avatar")
    val avatar: String? = null,
    @SerialName("is_parent")
    val isParent: Boolean? = null,
    @SerialName("account_id")
    val accountId: String? = null,
    @SerialName("email")
    val email: String? = null

)
