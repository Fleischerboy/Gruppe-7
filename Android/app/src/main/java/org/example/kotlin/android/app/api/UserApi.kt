package org.example.kotlin.android.app.api

import org.example.kotlin.android.app.models.SignIn
import org.example.kotlin.android.app.models.SignUp
import org.example.kotlin.android.app.models.UserInfo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("signup")
    fun signUp(@Body userSignUpData: SignUp): Call<UserInfo>


    @POST("signin")
    fun signIn(@Body userSignInData: SignIn): Call<UserInfo>

}