package org.example.kotlin.android.app.ui.home.inbox.chat_system.chats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.example.kotlin.android.app.data.repository.ChatsRepository
import org.example.kotlin.android.app.data.responses.ChatResponse
import org.example.kotlin.android.app.data.restapi.ChatsApi
import org.example.kotlin.android.app.data.restapi.Resource
import org.example.kotlin.android.app.databinding.FragmentChatsBinding
import org.example.kotlin.android.app.ui.base.BaseFragment
import org.example.kotlin.android.app.ui.handleApiError


class ChatsFragment : BaseFragment<ChatsViewModel, FragmentChatsBinding, ChatsRepository>() {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userId = runBlocking {userPreferences.getUserId.first()}.toString()
        val chatSellingAdapter = ChatsListAdapter()
        val chatBuyingAdapter = ChatsListAdapter()
        viewModel.getAllChatsByUserId(userId, "sellerId")
        viewModel.getAllChatsByUserId(userId, "buyerId")

        chatSellingAdapter.setOnClickListener(object : ChatsViewHolderInterface {
            override fun onItemClickListener(chatResponse: ChatResponse, view: View) {
                println(chatResponse);
                val chatId = chatResponse.id;
                val productTitle = chatResponse.product.title
                val navController = view.findNavController();
                val action = ChatsFragmentDirections.actionChatsFragmentToChatFragment(chatId, productTitle, userId)
                navController.navigate(action);


            }



            

        })

        chatBuyingAdapter.setOnClickListener(object : ChatsViewHolderInterface {
            override fun onItemClickListener(chatResponse: ChatResponse, view: View) {
                println(chatResponse);
                val chatId = chatResponse.id;
                val productTitle = chatResponse.product.title
                val navController = view.findNavController();
                val action = ChatsFragmentDirections.actionChatsFragmentToChatFragment(chatId, productTitle, userId)
                navController.navigate(action);
            }

        })

        binding.apply {
            binding.SellingItemsRecyclerView.apply {
                adapter = chatSellingAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }


        viewModel.sellerChats.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Success -> {
                    println(it.value)
                    chatSellingAdapter.submitList(it.value)

                }
                is Resource.Loading -> {

                }
                is Resource.Failure -> {
                    handleApiError(it)
                }
            }
        }
        binding.apply {
            binding.buyingItemsRecyclerView.apply {
                adapter = chatBuyingAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }
        viewModel.buyerChats.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    println(it.value)
                    chatBuyingAdapter.submitList(it.value)
                }
                is Resource.Loading -> {

                }
                is Resource.Failure -> {
                    handleApiError(it)
                }
            }
        }
    }


    override fun getViewModel(): Class<ChatsViewModel> {
        return ChatsViewModel::class.java
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChatsBinding = FragmentChatsBinding.inflate(inflater, container, false);

    override fun getFragmentRepository(): ChatsRepository {
        val api = remoteDataSource.buildServiceApi(ChatsApi::class.java)
        return ChatsRepository(api);
    }


}