package org.example.kotlin.android.app.models

import com.google.gson.annotations.SerializedName

data class SignIn(
   @SerializedName("email") val userEmail: String?,
   @SerializedName("password") val userPassword: String?,
)
