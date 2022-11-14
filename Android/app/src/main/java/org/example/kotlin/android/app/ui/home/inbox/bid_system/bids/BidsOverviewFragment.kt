package org.example.kotlin.android.app.ui.home.inbox.bid_system.bids

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.example.kotlin.android.app.data.repository.BidRepository
import org.example.kotlin.android.app.data.responses.ProductResponse
import org.example.kotlin.android.app.data.restapi.BidApi
import org.example.kotlin.android.app.data.restapi.Resource
import org.example.kotlin.android.app.databinding.FragmentBidsBinding
import org.example.kotlin.android.app.ui.base.BaseFragment
import org.example.kotlin.android.app.ui.handleApiError
import org.example.kotlin.android.app.ui.home.explore.ProductInterface
import org.example.kotlin.android.app.ui.visible

class BidsOverviewFragment : BaseFragment<BidsViewModel,FragmentBidsBinding, BidRepository>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userId = runBlocking {userPreferences.getUserId.first()}.toString()
        viewModel.getAllProductsByUserId(userId)
        val productsWithBidAdapter = ProductsWithBidListAdapter()

        productsWithBidAdapter.setOnClickListener(object : ProductInterface {
            override fun onItemClickListener(product: ProductResponse) {
                val productId = product.id
                val action = BidsOverviewFragmentDirections.actionBidsFragmentToBidFragment(productId)
                findNavController().navigate(action)
            }
        })



        binding.apply {
            binding.ProductWithBidsRecycler.apply {
                adapter = productsWithBidAdapter
                layoutManager = LinearLayoutManager(context)
            }

        }

        viewModel.ownersProducts.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Success -> {
                    binding.bidsProgressBar.visible(false)
                    productsWithBidAdapter.submitList(it.value.toList())

                }
                is Resource.Loading -> {
                    binding.bidsProgressBar.visible(true)
                }
                is Resource.Failure -> {
                    binding.bidsProgressBar.visible(false)
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