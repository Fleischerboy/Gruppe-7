package org.example.kotlin.android.app.data.restapi

import org.example.kotlin.android.app.data.requestsBody.SellProduct
import org.example.kotlin.android.app.data.responses.ProductResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface SellApi {


    @POST("users/{userId}/createProduct")
    suspend fun sellProduct(@Path("userId") userId: String, @Body productFormData: SellProduct) : ProductResponse

}