package org.example.kotlin.android.app.services

import android.app.Activity
import com.google.android.material.internal.ContextUtils.getActivity
import org.example.kotlin.android.app.api.UserApi
import org.example.kotlin.android.app.models.SignIn
import org.example.kotlin.android.app.models.UserInfo
import org.example.kotlin.android.app.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRestApiService {


        fun signIn(userSignInData: SignIn, onResult: (UserInfo?) -> Unit) {
        val userLogin = RetrofitClient.buildService(UserApi::class.java);
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