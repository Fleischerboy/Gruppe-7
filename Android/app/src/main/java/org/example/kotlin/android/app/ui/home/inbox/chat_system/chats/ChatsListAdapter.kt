package org.example.kotlin.android.app.ui.home.inbox.chat_system.chats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.example.kotlin.android.app.data.responses.ChatResponse
import org.example.kotlin.android.app.databinding.ProductChatCardViewBinding

class ChatsListAdapter() : ListAdapter<ChatResponse, ChatsListAdapter.ChatCardViewHolder>(
    ProductComparator()
) {



    private lateinit var chatViewHolderInterface: ChatsViewHolderInterface
    fun setOnClickListener(chatViewHolder: ChatsViewHolderInterface) {
        this.chatViewHolderInterface = chatViewHolder;

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatCardViewHolder {
        val chatCard = ProductChatCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatCardViewHolder(chatCard)
    }

    override fun onBindViewHolder(holder: ChatCardViewHolder, position: Int) {
        val currentChatCard = getItem(position);
        println(currentChatCard)
        holder.bind(currentChatCard);

    }



    inner class ChatCardViewHolder(private val binding: ProductChatCardViewBinding) : RecyclerView.ViewHolder(binding.root) {
        private val chatCardViewHolder = binding.chatCardView
        fun bind(chatResponse: ChatResponse) {
            binding.apply {
            Glide.with(chatCardView).load(chatResponse.product.img).into(productImgItem)
                tvProductHeader.text = chatResponse.product.title
                tvProductDescription.text = chatResponse.createdAt
                chatCardViewHolder.setOnClickListener {
                    chatViewHolderInterface.onItemClickListener(chatResponse, it)
                }
            }

        }
    }

    class ProductComparator : DiffUtil.ItemCallback<ChatResponse>() {
        override fun areItemsTheSame(oldItem: ChatResponse, newItem: ChatResponse): Boolean {
                return true;
        }

        override fun areContentsTheSame(oldItem: ChatResponse, newItem: ChatResponse): Boolean {
            return true;
        }

    }

}
