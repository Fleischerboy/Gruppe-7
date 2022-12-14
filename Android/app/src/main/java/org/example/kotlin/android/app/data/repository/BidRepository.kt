package org.example.kotlin.android.app.data.repository

import org.example.kotlin.android.app.data.restapi.BidApi

class BidRepository(private val api: BidApi) : BaseRepository(){

    suspend fun getBidByBidId(bidId: String) {
        safeApiCall {
            api.getBidByBidId(bidId);
        }
    }

    suspend fun getBidsOnProduct(productId: String) = safeApiCall {
        api.getBidsOnProduct(productId) }

    suspend fun getOwnersProductById(userId: String) = safeApiCall {
        api.getUserProductsById(userId) }


    suspend fun acceptBid(bidId: String) = safeApiCall {
        api.acceptBid(bidId)
    }
}