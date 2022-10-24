package org.example.kotlin.android.app.ui.home.explore

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.delay
import org.example.kotlin.android.app.data.repository.ProductRepository
import org.example.kotlin.android.app.data.responses.ProductResponse
import org.example.kotlin.android.app.data.restapi.ProductApi
import org.example.kotlin.android.app.data.restapi.Resource
import org.example.kotlin.android.app.databinding.FragmentExploreBinding
import org.example.kotlin.android.app.ui.base.BaseFragment
import org.example.kotlin.android.app.ui.handleApiError
import org.example.kotlin.android.app.ui.home.HomeActivity
import org.example.kotlin.android.app.ui.home.productOverview.ProductActivity
import org.example.kotlin.android.app.ui.startNewActivity
import org.example.kotlin.android.app.ui.visible


class ExploreFragment : BaseFragment<ExploreViewModel, FragmentExploreBinding, ProductRepository>() {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.exploreProgressBar.visible(true);
        viewModel.getAllProducts()
        val productAdapter = ProductListAdapter()

        productAdapter.setOnClickListener(object  : ProductInterface {
            override fun onItemClickListener(product: ProductResponse) {
                println(product.id)
                val productActivityIntent = Intent(context, ProductActivity::class.java)
                val productId = product.id;
                productActivityIntent.putExtra("product_id", productId)
                startActivity(productActivityIntent)


            }

        })


        binding.apply {
            binding.RecyclerViewProducts.apply {
                adapter = productAdapter
                layoutManager = LinearLayoutManager(context)
            }
            viewModel.products.observe(viewLifecycleOwner) {
                when(it) {
                    is Resource.Success -> {
                        binding.exploreProgressBar.visible(false);
                        productAdapter.submitList(it.value.toList())

                    }
                    is Resource.Loading -> {
                        binding.exploreProgressBar.visible(true);
                    }
                    is Resource.Failure -> {
                        handleApiError(it)
                    }
                }

            }
        }








    }



    override fun getViewModel(): Class<ExploreViewModel> {
        return ExploreViewModel::class.java
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentExploreBinding = FragmentExploreBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): ProductRepository {
        val api = remoteDataSource.buildServiceApi(ProductApi::class.java)
        return ProductRepository(api)
    }


}