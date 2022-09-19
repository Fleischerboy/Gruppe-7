package org.example.kotlin.android.app.data.restapi

import okhttp3.ResponseBody
import org.example.kotlin.android.app.data.responses.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {

    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") userId: String): UserResponse


    @POST("logout")
    suspend fun logout(): ResponseBody

}