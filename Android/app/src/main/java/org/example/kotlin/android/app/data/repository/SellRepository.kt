package org.example.kotlin.android.app.data.repository

import org.example.kotlin.android.app.data.requestsBody.SellProduct
import org.example.kotlin.android.app.data.restapi.SellApi

class SellRepository(
    private val api: SellApi
) : BaseRepository() {

    suspend fun sellProduct(userId: String, sellProduct: SellProduct) = safeApiCall {
        api.sellProduct(userId, sellProduct)
    }



}