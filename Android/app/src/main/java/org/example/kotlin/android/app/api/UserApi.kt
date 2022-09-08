package org.example.kotlin.android.app.APIs

import org.example.kotlin.android.app.models.SignIn
import org.example.kotlin.android.app.models.SignUp
import org.example.kotlin.android.app.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("/signup")
    fun signUp(@Body userData: SignUp): Call<User>


    @POST("/signin")
    fun signIn(@Body userData: SignIn): Call<User>

}