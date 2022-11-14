package org.example.kotlin.android.app.data.restapi

import kotlinx.coroutines.flow.Flow
import org.example.kotlin.android.app.data.responses.BidResponse
import org.example.kotlin.android.app.data.responses.ProductResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductApi {


    @GET("products")
    suspend fun getAllProducts(): List<ProductResponse>

    @GET("products/{productId}")
    suspend fun getProductById(@Path("productId") productId: Int): ProductResponse


    @POST("products/{productId}/createBid")
    suspend fun createBid(@Path("productId") productId: String, @Body bidAmount: String, @Body userId: Int): BidResponse

    /*
    @GET("bids")
    suspend fun getAllBids(): List<BidResponse>

     */

}