package org.example.kotlin.android.app.data.restapi

import org.example.kotlin.android.app.data.responses.BidResponse
import org.example.kotlin.android.app.data.responses.ChatResponse
import org.example.kotlin.android.app.data.responses.ProductResponse
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface BidApi {

    @GET("bids/{bidId}")
    suspend fun getBidByBidId(@Path("bidId") bidId: String): BidResponse

    @GET("bids/product/{productId}")
    suspend fun getBidsOnProduct(@Path("productId") userId: String): List<BidResponse>

    @GET("users/{userId}/products")
    suspend fun getUserProductsById(@Path("userId") userId: String): List<ProductResponse>

    @PUT("bids/{bidId}/acceptBid")
    suspend fun acceptBid(@Path("bidId") bidId: String) : ChatResponse


}