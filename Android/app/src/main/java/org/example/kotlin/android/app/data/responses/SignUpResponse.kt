package org.example.kotlin.android.app.data.responses

data class SignUpResponse(
    val createdAt: String,
    val email: String,
    val encrypted_password: String,
    val fullname: String,
    val id: Int,
    val salt: String,
    val unique_id: String,
    val updatedAt: String
)