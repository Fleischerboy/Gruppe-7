package org.example.kotlin.android.app.data.requestsBody

import android.net.Uri
import androidx.lifecycle.LiveData

data class SellProduct(
    val ownerId: String,
    val title: String,
    val imageUrl: String,
    val productPrice: String,
    val description: String,
    val address: String,
    )