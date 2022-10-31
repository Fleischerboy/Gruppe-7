package org.example.kotlin.android.app.ui.home.inbox.chat_system.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import org.example.kotlin.android.app.data.repository.ChatRepository
import org.example.kotlin.android.app.data.requestsBody.Message
import org.example.kotlin.android.app.data.responses.MessageResponse
import org.example.kotlin.android.app.data.restapi.ChatApi
import org.example.kotlin.android.app.data.restapi.Resource
import org.example.kotlin.android.app.databinding.FragmentChatBinding
import org.example.kotlin.android.app.ui.base.BaseFragment
import org.example.kotlin.android.app.ui.handleApiError

class ChatFragment : BaseFragment<ChatViewModel, FragmentChatBinding, ChatRepository>() {
    private val args: ChatFragmentArgs by navArgs()




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // get all messages
        val userId = args.userId
        val chatId = args.chatId.toString()
        val productTitle = args.productTitle;



        viewModel.getAllMessagesByChatId(userId, chatId)

            viewModel.messages.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Success -> {
                        println(it.value)
                        updateUI(it.value, userId.toInt())

                    }
                    is Resource.Loading -> {

                    }
                    is Resource.Failure -> {
                        handleApiError(it)
                    }
                }


            }

        viewModel.message.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Success -> {
                    println(it.value)

                }
                is Resource.Loading -> {

                }
                is Resource.Failure -> {
                    handleApiError(it)
                }
            }
        }


            binding.sendBtn.setOnClickListener {
                val msg = binding.messageBox.text.toString();
                println(msg)
                val message = Message(msg)
                viewModel.createMessageToASpecificChat(userId, chatId, message)
                binding.messageBox.setText("");

            }

        }






    private fun updateUI(messageList: List<MessageResponse>, userId: Int) {
        with(binding) {
            val messageAdapter =
                activity?.let { MessageAdapter(it.applicationContext, messageList.toList(), userId) }
            binding.chatRecyclerView.apply {
                adapter = messageAdapter;
                layoutManager = LinearLayoutManager(context)
            }

        }
    }



    override fun getViewModel(): Class<ChatViewModel> {
        return ChatViewModel::class.java
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChatBinding = FragmentChatBinding.inflate(inflater, container, false);

    override fun getFragmentRepository(): ChatRepository {
        val api = remoteDataSource.buildServiceApi(ChatApi::class.java);
        return ChatRepository(api);
    }


}