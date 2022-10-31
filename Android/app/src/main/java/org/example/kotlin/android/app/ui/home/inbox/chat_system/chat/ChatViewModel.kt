package org.example.kotlin.android.app.ui.home.inbox.chat_system.chat


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.example.kotlin.android.app.data.repository.ChatRepository
import org.example.kotlin.android.app.data.requestsBody.Message
import org.example.kotlin.android.app.data.responses.ChatResponse
import org.example.kotlin.android.app.data.responses.MessageResponse
import org.example.kotlin.android.app.data.restapi.Resource
import org.example.kotlin.android.app.ui.base.BaseViewModel

class ChatViewModel(
    private val repository: ChatRepository
    ) : BaseViewModel(repository) {

    private val _messages: MutableLiveData<Resource<List<MessageResponse>>> = MutableLiveData()
    private val _message: MutableLiveData<Resource<MessageResponse>> = MutableLiveData()

    val messages: LiveData<Resource<List<MessageResponse>>>
        get() = _messages

    val message: LiveData<Resource<MessageResponse>>
        get() = _message



    fun getAllMessagesByChatId(userId: String, chatId: String) = viewModelScope.launch {
        repository.getAllMessagesByChatId(userId, chatId).collect {
            _messages.value = it;
        }
    }

    fun createMessageToASpecificChat(userId: String, chatId: String, message: Message) = viewModelScope.launch {
        _message.value = repository.createMessageToASpecificChat(userId, chatId, message);
    }


}

