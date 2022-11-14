package org.example.kotlin.android.app.data.responses

data class BidResponse (
    val id: Int,
    val product: ProductResponse,
    val productId: Int,
    val productOwnerId: Int,
    val bidUser: UserResponse,
    val bidUserId: Int,
    val bidAmount: Float,
    val isBidAccepted: Boolean
)

