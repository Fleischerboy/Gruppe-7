package org.example.kotlin.android.app.data.restapi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.example.kotlin.android.app.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {


    companion object {
        private const val BASE_URL = "https://gruppe7-andorid-app-api.herokuapp.com/"
    }


    // Generic function that can build an instance for any api interface.
    fun<Api> buildServiceApi(api: Class<Api>, authToken: String? = null) : Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder()
                .addInterceptor { chain ->
                    chain.proceed(chain.request().newBuilder().also {
                        it.addHeader("x-access-token", authToken.toString())
                    }.build())
                }.also { client ->
                if(BuildConfig.DEBUG) {
                    val logging =  HttpLoggingInterceptor();
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    // added logging interceptor to our okhttp client.
                    // we will now be able to see all the requests and responses in our log. so that will help us with the debugging
                    client.addInterceptor(logging);
                }
            }.build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api);
    }
}