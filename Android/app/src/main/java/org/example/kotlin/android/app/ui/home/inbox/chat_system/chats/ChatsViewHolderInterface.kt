package org.example.kotlin.android.app.ui.home.inbox.chat_system.chats

import android.view.View
import org.example.kotlin.android.app.data.responses.ChatResponse

interface ChatsViewHolderInterface {

    fun onItemClickListener(chatResponse: ChatResponse, view: View)
}