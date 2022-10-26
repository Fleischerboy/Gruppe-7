package org.example.kotlin.android.app.data.repository

import org.example.kotlin.android.app.data.restapi.ChatsApi

class ChatsRepository(
    private val api: ChatsApi,
) : BaseRepository() {


    suspend fun getAllChatsByUserId(userId: String, chatType: String) = safeApiCall {
        api.getAllChatsByUserId(userId, chatType)
    }


}