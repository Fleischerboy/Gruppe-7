package org.example.kotlin.android.app.ui.home.inbox.bid_system.bids

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.example.kotlin.android.app.data.repository.BidRepository
import org.example.kotlin.android.app.data.restapi.BidApi
import org.example.kotlin.android.app.databinding.FragmentBidsBinding
import org.example.kotlin.android.app.ui.base.BaseFragment

class BidsFragment : BaseFragment<BidsViewModel,FragmentBidsBinding, BidRepository>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textView3.text = "YAYY!"
    }

    override fun getViewModel(): Class<BidsViewModel> {
       return BidsViewModel::class.java
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBidsBinding = FragmentBidsBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): BidRepository {
        val api = remoteDataSource.buildServiceApi(BidApi::class.java)
        return BidRepository(api);
    }


}