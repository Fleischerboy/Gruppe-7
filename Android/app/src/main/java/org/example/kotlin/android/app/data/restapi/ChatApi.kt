package org.example.kotlin.android.app.data.restapi

import org.example.kotlin.android.app.data.requestsBody.Message
import org.example.kotlin.android.app.data.responses.MessageResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChatApi {


    @GET("chats/{userId}/{chatId}/messages")
    suspend fun getAllMessagesByChatId(@Path("userId") userId: String, @Path("chatId") chatId: String): List<MessageResponse>

    @POST("chats/{userId}/{chatId}/newMessage")
    suspend fun createMessageToASpecificChat(@Path("userId") userId: String, @Path("chatId") chatId: String, @Body message: Message): MessageResponse


}