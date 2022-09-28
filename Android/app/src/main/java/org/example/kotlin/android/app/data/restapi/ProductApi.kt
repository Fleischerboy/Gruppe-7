package org.example.kotlin.android.app.data.restapi

import org.example.kotlin.android.app.data.responses.ProductResponse
import retrofit2.http.GET

interface ProductApi {


    @GET("products")
    suspend fun getAllProducts(): List<ProductResponse>
}