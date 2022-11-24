package org.example.kotlin.android.app.ui.home.productOverview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.example.kotlin.android.app.R
import org.example.kotlin.android.app.data.repository.ProductRepository
import org.example.kotlin.android.app.data.requestsBody.Bid
import org.example.kotlin.android.app.data.responses.BidResponse
import org.example.kotlin.android.app.data.responses.ProductResponse
import org.example.kotlin.android.app.data.restapi.ProductApi
import org.example.kotlin.android.app.data.restapi.Resource
import org.example.kotlin.android.app.databinding.FragmentProductOverviewBinding
import org.example.kotlin.android.app.ui.base.BaseFragment
import org.example.kotlin.android.app.ui.handleApiError
import org.example.kotlin.android.app.ui.visible


class ProductOverviewFragment : BaseFragment<ProductViewModel, FragmentProductOverviewBinding, ProductRepository>() {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productId = (activity as ProductActivity).productId
        val userId = runBlocking {userPreferences.getUserId.first()}!!.toInt()

        println(productId)
        viewModel.getProduct(productId);

        viewModel.product.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Success -> {
                    updateProductOverviewUI(it.value)
                    binding.bidButton.setOnClickListener {
                        createBid(productId, userId)
                    }
                    binding.mapsButton.setOnClickListener {
                        navigateToMaps()
                    }
                }
                is Resource.Failure -> {
                    handleApiError(it)
                }
            }
        }

        viewModel.bid.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Success -> {
                    Toast.makeText(context,"You successfully bid: " + it.value.bidAmount, Toast.LENGTH_SHORT).show()
                    binding.bidInput.text = null;
                }
                is Resource.Failure -> {
                    Log.d("API fetch error", "Error code: " + it.errorCode)
                    handleApiError(it)
                }
            }
        }
    }

    private fun createBid(productId: Int, userId: Int) {
        val bidAmount = binding.bidInput.text.toString()
        val bid = Bid(bidAmount, userId)
        if (bidAmount.toDouble() <= 0) Toast.makeText(context,"The amount is to low to make a bid", Toast.LENGTH_LONG).show()
        else viewModel.createBid(productId.toString(), bid)
    }

    private fun updateProductOverviewUI(product: ProductResponse) {
        with(binding) {
            productTitle.text = product.title;
            description.text = product.description;
            currentBid.text = product.productPrice.toString()
            Picasso.get().load(product.imageUrl).into(imageView);
        }
    }

    private fun navigateToMaps(){
        val action = ProductOverviewFragmentDirections.actionProductOverviewFragmentToMapsFragment()
        action.latitude = 59.132164F
        action.longitude = 11.352F
        findNavController().navigate(action)
    }


    override fun getViewModel(): Class<ProductViewModel> {
      return ProductViewModel::class.java
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProductOverviewBinding = FragmentProductOverviewBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): ProductRepository {
        val token = runBlocking {  userPreferences.getAccessToken.first() }
        val api = remoteDataSource.buildServiceApi(ProductApi::class.java, token)
        return ProductRepository(api)
    }


}