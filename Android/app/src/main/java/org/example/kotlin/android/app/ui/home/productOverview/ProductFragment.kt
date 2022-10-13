package org.example.kotlin.android.app.ui.home.productOverview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import org.example.kotlin.android.app.data.repository.ProductRepository
import org.example.kotlin.android.app.data.responses.ProductResponse
import org.example.kotlin.android.app.data.restapi.ProductApi
import org.example.kotlin.android.app.data.restapi.Resource
import org.example.kotlin.android.app.databinding.FragmentExploreBinding
import org.example.kotlin.android.app.databinding.FragmentProductDetailBinding
import org.example.kotlin.android.app.ui.base.BaseFragment
import org.example.kotlin.android.app.ui.handleApiError
import org.example.kotlin.android.app.ui.home.HomeActivity
import org.example.kotlin.android.app.ui.home.explore.ExploreFragment
import org.example.kotlin.android.app.ui.home.explore.ExploreViewModel
import org.example.kotlin.android.app.ui.visible

//TODO: display data

class ProductFragment : BaseFragment<ProductViewModel, FragmentProductDetailBinding, ProductRepository>(){
//    private val args : ProductFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var productId = (activity as? ProductActivity)?.productId
        if (productId != null) {
            print(productId)
            viewModel.getProduct(productId)

        }
        binding.productTitle.text = "Hva faen!"
////


        viewModel.product.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
//                    binding.progressBar2.visible(false)
//                    binding.textView.text = "it.value.title"
////                    binding.currentBid.text = it.value.title
//                    binding.description.text = it.value.description
//                    Glide.with(this)
//                        .load(it.value.imageUrl)
//                        .into(binding.imageView);
                }
                is Resource.Loading -> {
//                    binding.progressBar2.visible(true)
                }
                is Resource.Failure -> {
                    handleApiError(it)
                }
            }
        })




    }



    override fun getViewModel(): Class<ProductViewModel> = ProductViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProductDetailBinding = FragmentProductDetailBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): ProductRepository {
        val api = remoteDataSource.buildServiceApi(ProductApi::class.java)
        return ProductRepository(api)
    }
}


