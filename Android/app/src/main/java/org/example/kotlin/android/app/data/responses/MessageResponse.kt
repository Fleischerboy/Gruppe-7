package org.example.kotlin.android.app.data.responses

data class MessageResponse(
    val chatId: Int,
    val createdAt: String,
    val id: Int,
    val message: String? = null,
    val updatedAt: String,
    val userId: Int
)