package org.example.kotlin.android.app.data.requestsBody

import com.google.gson.annotations.SerializedName

data class SignUp(
  @SerializedName("fullname") val userFullName: String?,
  @SerializedName("email") val userEmail: String?,
  @SerializedName("password") val userPassword: String?,
)
