package org.example.kotlin.android.app.services

import org.example.kotlin.android.app.restapi.ApiInterface
import org.example.kotlin.android.app.models.SignIn
import org.example.kotlin.android.app.models.UserInfo
import org.example.kotlin.android.app.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserService {


        fun signIn(userSignInData: SignIn, onResult: (UserInfo?) -> Unit) {
        val userLogin = RetrofitClient.buildService(ApiInterface::class.java);
        userLogin.signIn(userSignInData).enqueue(
            object : Callback<UserInfo> {
                override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                    if(response.isSuccessful) {
                        val userInfo = response.body();
                        onResult(userInfo);
                    }

                }
                override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                    println("error: " + t.message);
                    onResult(null);
                }
            }
        )
    }
}