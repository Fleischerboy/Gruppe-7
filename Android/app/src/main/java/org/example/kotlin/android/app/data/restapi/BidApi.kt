package org.example.kotlin.android.app.data.restapi

import org.example.kotlin.android.app.data.responses.BidResponse
import org.example.kotlin.android.app.data.responses.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BidApi {

    @GET("api/bids/{bidId}")
    suspend fun getBidByBidId(@Path("bidId") bidId: String): BidResponse

    //TODO Get bids from specific product
    @GET("users/{userId}/products")
    suspend fun getUserProductsById(@Path("userId") userId: String): List<ProductResponse>


}