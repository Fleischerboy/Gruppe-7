package org.example.kotlin.android.app.ui.home.inbox.bid_system

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.example.kotlin.android.app.R
import org.example.kotlin.android.app.databinding.ActivityBidBinding
import org.example.kotlin.android.app.databinding.ActivityChatBinding

class BidActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBidBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBidBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}