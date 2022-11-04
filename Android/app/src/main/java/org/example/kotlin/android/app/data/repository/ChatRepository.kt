package org.example.kotlin.android.app.data.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.example.kotlin.android.app.data.requestsBody.Message
import org.example.kotlin.android.app.data.responses.MessageResponse
import org.example.kotlin.android.app.data.restapi.ChatApi
import org.example.kotlin.android.app.data.restapi.Resource
import retrofit2.http.Path

class ChatRepository(
    private val api: ChatApi
) : BaseRepository() {


    suspend fun getAllMessagesByChatId(userId: String, chatId: String): Flow<Resource<List<MessageResponse>>> = flow {
        while (true) {
            val latestMessages = api.getAllMessagesByChatId(userId, chatId)
            emit(safeApiCall { latestMessages })
            delay(1000)

        }
    }


    suspend fun createMessageToASpecificChat(userId: String, chatId: String, message: Message) = safeApiCall {
        api.createMessageToASpecificChat(userId, chatId, message)
    }

}