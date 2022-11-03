package org.example.kotlin.android.app.data.repository

import org.example.kotlin.android.app.data.restapi.BidApi

class BidRepository(
    private val api: BidApi
) : BaseRepository() {

    suspend fun bids() = safeApiCall {

    }

    
}