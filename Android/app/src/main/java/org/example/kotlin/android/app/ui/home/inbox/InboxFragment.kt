package org.example.kotlin.android.app.ui.home.inbox

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.example.kotlin.android.app.data.repository.InboxRepository
import org.example.kotlin.android.app.data.restapi.InboxApi
import org.example.kotlin.android.app.databinding.FragmentInboxBinding
import org.example.kotlin.android.app.ui.base.BaseFragment
import org.example.kotlin.android.app.ui.home.inbox.chat_system.ChatActivity


class InboxFragment : BaseFragment<InboxViewModel, FragmentInboxBinding, InboxRepository>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.btnChats.setOnClickListener {
            val chatActivityIntent = Intent(it.context, ChatActivity::class.java)
            startActivity(chatActivityIntent)
        }

    }


    override fun getViewModel(): Class<InboxViewModel> {
        return InboxViewModel::class.java
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInboxBinding = FragmentInboxBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): InboxRepository {
        val api = remoteDataSource.buildServiceApi(InboxApi::class.java)
        return InboxRepository(api);
    }


}