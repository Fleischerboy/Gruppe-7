package org.example.kotlin.android.app.data.repository

import org.example.kotlin.android.app.data.restapi.ProductApi

class ProductRepository(

    private val api: ProductApi
) : BaseRepository() {


    suspend fun getAllProducts() = safeApiCall {
        api.getAllProducts();
    }

    suspend fun getProductById(id : Int) = safeApiCall {
        api.getProductById(id);
    }

    suspend fun getAllBids() = safeApiCall {
        api.getAllBids()
    }

}