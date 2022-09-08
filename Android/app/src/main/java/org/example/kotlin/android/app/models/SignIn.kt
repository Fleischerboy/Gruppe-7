package org.example.kotlin.android.app.models

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("user_email") val userEmail: String?,
    @SerializedName("user_password") val userPassword: String?,
)
