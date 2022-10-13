package org.example.kotlin.android.app.ui.home.productOverview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import org.example.kotlin.android.app.R
import org.example.kotlin.android.app.data.repository.ProductRepository
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

        var productId = (activity as ProductActivity).productId
        println(productId)
        viewModel.getProduct(productId);

        viewModel.product.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Success -> {
                    updateProductOverviewUI(it.value)

                }
                is Resource.Failure -> {
                    handleApiError(it)
                }
            }
        }
    }

    private fun updateProductOverviewUI(product: ProductResponse) {
        with(binding) {
            productTitle.text = product.title;
            description.text = product.description;
            currentBid.text = "200"
            Picasso.get().load(product.imageUrl).into(imageView);


        }
    }


    override fun getViewModel(): Class<ProductViewModel> {
      return ProductViewModel::class.java
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProductOverviewBinding = FragmentProductOverviewBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): ProductRepository {
        val api = remoteDataSource.buildServiceApi(ProductApi::class.java)
        return ProductRepository(api)
    }


}