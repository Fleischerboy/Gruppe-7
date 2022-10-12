package org.example.kotlin.android.app.data.responses

data class UserResponse(
    val createdAt: String,
    val profileImageUrl: String?,
    val email: String,
    val fullname: String,
    val id: Int

)