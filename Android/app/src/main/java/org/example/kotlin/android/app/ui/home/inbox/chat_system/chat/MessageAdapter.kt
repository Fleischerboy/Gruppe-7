package org.example.kotlin.android.app.ui.home.inbox.chat_system.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.example.kotlin.android.app.data.responses.MessageResponse
import org.example.kotlin.android.app.databinding.FragmentChatBinding
import org.example.kotlin.android.app.databinding.ReceiveBinding
import org.example.kotlin.android.app.databinding.SentBinding


class MessageAdapter(val context: Context, val messageList: List<MessageResponse>, val clientUserId: Int): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECIVED = 1;
    val ITEM_SENT = 2;


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == 1) {
            //inflate receive
            val receive =
                ReceiveBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ReceiveViewHolder(receive)

        }
        else {
            //inflate sent
            val sent = SentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return SentViewHolder(sent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage = messageList[position]
        if(holder.javaClass == SentViewHolder::class.java) {
            val viewHolder = holder as SentViewHolder
            holder.sentMessage.text = currentMessage.message

        } else {
            val viewHolder = holder as ReceiveViewHolder
            holder.receiveMessage.text = currentMessage.message

        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]
        if(currentMessage.userId.equals(clientUserId)) {
            return ITEM_SENT
        }else {
            return ITEM_RECIVED
        }
    }

    override fun getItemCount(): Int {
       return messageList.size
    }


    class SentViewHolder(private val binding: SentBinding) : RecyclerView.ViewHolder(binding.root) {
        val sentMessage: TextView = binding.txtSentMessage

    }
    class ReceiveViewHolder(private val binding: ReceiveBinding) : RecyclerView.ViewHolder(binding.root) {
        val receiveMessage = binding.txtReceiveMessage
    }
}