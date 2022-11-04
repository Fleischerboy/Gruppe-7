package org.example.kotlin.android.app.ui.home.inbox.bid_system.bids

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.example.kotlin.android.app.data.repository.BidRepository
import org.example.kotlin.android.app.data.restapi.BidApi
import org.example.kotlin.android.app.data.restapi.Resource
import org.example.kotlin.android.app.databinding.FragmentBidsBinding
import org.example.kotlin.android.app.ui.base.BaseFragment
import org.example.kotlin.android.app.ui.handleApiError

class BidsOverviewFragment : BaseFragment<BidsViewModel,FragmentBidsBinding, BidRepository>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userId = runBlocking {userPreferences.getUserId.first()}.toString()
        viewModel.getAllProductsByUserId(userId)
        val productsWithBidAdapter = ProductsWithBidListAdapter()

        binding.textViewBids.text = "WTF!"

        binding.apply {
            binding.ProductWithBidsRecycler.apply {
                adapter = productsWithBidAdapter
                layoutManager = LinearLayoutManager(context)
            }

        }

        viewModel.ownersProducts.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Success -> {
                    //binding.exploreProgressBar.visible(false);
                    productsWithBidAdapter.submitList(it.value.toList())

                }
                is Resource.Loading -> {
                    //binding.exploreProgressBar.visible(true);
                }
                is Resource.Failure -> {
                    handleApiError(it)
                }
            }

        }

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