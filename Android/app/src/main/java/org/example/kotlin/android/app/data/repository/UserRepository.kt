package org.example.kotlin.android.app.data.repository

import org.example.kotlin.android.app.data.restapi.ProductApi
import org.example.kotlin.android.app.data.restapi.UserApi

class UserRepository(
    private val userApi: UserApi

) : BaseRepository() {

   suspend fun getUser(userId: String) = safeApiCall {
       userApi.getUser(userId)

   }

    suspend fun getUserProductsById(userId: String) = safeApiCall {
        userApi.getUserProductsById(userId);
    }

}