package org.example.kotlin.android.app.ui.home.inbox.chat_system

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.example.kotlin.android.app.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}