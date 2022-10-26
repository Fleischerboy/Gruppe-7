package org.example.kotlin.android.app.data.responses

data class ChatResponse(
    val buyerId: Int,
    val createdAt: String,
    val id: Int,
    val productId: Int,
    val product: ProductChatInfo,
    val sellerId: Int,
    val updatedAt: String
)