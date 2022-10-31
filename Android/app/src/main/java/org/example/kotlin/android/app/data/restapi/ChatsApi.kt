package org.example.kotlin.android.app.data.restapi

import org.example.kotlin.android.app.data.responses.ChatResponse
import org.example.kotlin.android.app.data.responses.MessageResponse
import retrofit2.http.*

interface ChatsApi {

    @GET("chats/{userId}")
    suspend fun getAllChatsByUserId(@Path("userId") userId: String, @Query("chatType") chatType: String): List<ChatResponse>


    @GET("chats/{userId}/{chatId}")
    suspend fun getUserChatById(@Path("userId") userId: String, @Path("chatId") chatId: String): ChatResponse



}