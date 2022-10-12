package org.example.kotlin.android.app.data.responses

data class ProductResponse(
    val address: String,
    val description: String,
    val id: Int,
    val imageUrl: String,
    val productPrice: Double,
    val ownerId: Int,
    val title: String
)