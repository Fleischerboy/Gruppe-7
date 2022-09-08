package org.example.kotlin.android.app

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {


    private const val BASEURL = ""; //


    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}