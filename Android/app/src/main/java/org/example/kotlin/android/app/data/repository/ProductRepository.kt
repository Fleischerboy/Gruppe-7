package org.example.kotlin.android.app.data.repository

import androidx.lifecycle.asLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import kotlinx.coroutines.flow.flowOn
import org.example.kotlin.android.app.data.responses.ProductResponse
import org.example.kotlin.android.app.data.restapi.ProductApi
import org.example.kotlin.android.app.data.restapi.Resource

class ProductRepository(

    private val api: ProductApi
) : BaseRepository() {


    suspend fun getAllProducts(): Flow<Resource<List<ProductResponse>>> = flow {
       while(true) {
           val latestProducts = api.getAllProducts()
           emit(safeApiCall { latestProducts })
           delay(5000)
       }
    }



    suspend fun getProductById(id : Int) = safeApiCall {
        api.getProductById(id);
    }



    /*
    suspend fun getAllBids() = safeApiCall {
        api.getAllBids()
    }

     */

}