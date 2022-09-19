package org.example.kotlin.android.app.data.repository

import org.example.kotlin.android.app.data.restapi.UserApi

class UserRepository(private val api: UserApi) : BaseRepository() {

   suspend fun getUser(userId: String) = safeApiCall {
       api.getUser(userId)

   }

}