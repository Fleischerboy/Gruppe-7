package org.example.kotlin.android.app.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val userId: Int,
    @SerializedName("fullname") val userFullName: String,
    @SerializedName("email") val userEmail: String,
    )
