package org.example.kotlin.android.app.ui.home.inbox.bid_system.bid

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import org.example.kotlin.android.app.R
import org.example.kotlin.android.app.data.repository.BidRepository
import org.example.kotlin.android.app.data.responses.BidResponse
import org.example.kotlin.android.app.data.restapi.BidApi
import org.example.kotlin.android.app.data.restapi.Resource
import org.example.kotlin.android.app.databinding.FragmentBidBinding
import org.example.kotlin.android.app.ui.base.BaseFragment
import org.example.kotlin.android.app.ui.handleApiError


class BidFragment : BaseFragment<BidViewModel, FragmentBidBinding, BidRepository>() {
    private val args : BidFragmentArgs by navArgs()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val productId = args.uid.toString()
        viewModel.getBidsOnProduct(productId)
        val bidListAdapter = BidListAdapter()

        bidListAdapter.setOnClickListener(object : BidInterface {
            override fun onItemClickListener(bid: BidResponse) {
                viewModel.acceptBid(bid.id.toString())
                Toast.makeText(context,"A chat was created!",Toast.LENGTH_SHORT).show()
            }
        })


        binding.apply {
            binding.BidsRecycler.apply {
                adapter = bidListAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }

        viewModel.bids.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Success -> {
//                    binding.bidsProgressBar.visible(false)

                    bidListAdapter.submitList(it.value.toList())
                }
                is Resource.Loading -> {
//                    binding.bidsProgressBar.visible(true)
                }
                is Resource.Failure -> {
                    handleApiError(it)
                }
            }

        }


    }








    override fun getViewModel(): Class<BidViewModel> {
        return BidViewModel::class.java
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBidBinding {
        return FragmentBidBinding.inflate(inflater, container, false)
    }

    override fun getFragmentRepository(): BidRepository {
        val api = remoteDataSource.buildServiceApi(BidApi::class.java)
        return BidRepository(api)
    }

}