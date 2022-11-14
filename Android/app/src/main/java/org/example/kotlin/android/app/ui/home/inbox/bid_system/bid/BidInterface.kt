package org.example.kotlin.android.app.ui.home.inbox.bid_system.bid

import org.example.kotlin.android.app.data.responses.BidResponse

interface BidInterface {
    fun onItemClickListener(bid : BidResponse)

}