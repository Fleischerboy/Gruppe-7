package org.example.kotlin.android.app.restapi

import org.example.kotlin.android.app.models.SignIn
import org.example.kotlin.android.app.models.SignUp
import org.example.kotlin.android.app.models.UserInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("signup")
     fun signUp(@Body userSignUpData: SignUp): Call<UserInfo>


    @POST("signin")
     fun signIn(@Body userSignInData: SignIn): Call<UserInfo>

}