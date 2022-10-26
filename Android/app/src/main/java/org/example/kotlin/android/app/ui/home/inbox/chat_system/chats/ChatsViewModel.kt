package org.example.kotlin.android.app.ui.home.inbox.chat_system.chats

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.example.kotlin.android.app.data.repository.ChatsRepository
import org.example.kotlin.android.app.data.responses.ChatResponse
import org.example.kotlin.android.app.data.restapi.Resource
import org.example.kotlin.android.app.ui.base.BaseViewModel

class ChatsViewModel(
    private val repository: ChatsRepository
) : BaseViewModel(repository) {

    private val _sellerChats: MutableLiveData<Resource<List<ChatResponse>>> = MutableLiveData()
    private val _buyerChats: MutableLiveData<Resource<List<ChatResponse>>> = MutableLiveData()

    val sellerChats: LiveData<Resource<List<ChatResponse>>>
        get() = _sellerChats;

    val buyerChats: LiveData<Resource<List<ChatResponse>>>
        get() = _buyerChats;


    fun getAllChatsByUserId(userId: String, chatType: String)  = viewModelScope.launch {
         when(chatType) {
             "sellerId" -> _sellerChats.value = repository.getAllChatsByUserId(userId, chatType);
             "buyerId" -> _buyerChats.value = repository.getAllChatsByUserId(userId, chatType);
             else -> {
                 println("something went wrong")
             }
         }


        }

    }
