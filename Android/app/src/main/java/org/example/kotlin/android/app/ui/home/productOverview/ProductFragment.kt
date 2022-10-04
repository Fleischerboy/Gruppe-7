package org.example.kotlin.android.app.ui.home.productOverview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import org.example.kotlin.android.app.data.repository.ProductRepository
import org.example.kotlin.android.app.data.responses.ProductResponse
import org.example.kotlin.android.app.data.restapi.ProductApi
import org.example.kotlin.android.app.databinding.FragmentExploreBinding
import org.example.kotlin.android.app.databinding.FragmentProductBinding
import org.example.kotlin.android.app.ui.base.BaseFragment
import org.example.kotlin.android.app.ui.home.explore.ExploreFragment
import org.example.kotlin.android.app.ui.home.explore.ExploreViewModel
//TODO: display data

class ProductFragment : BaseFragment<ProductViewModel, FragmentProductBinding, ProductRepository>(){
    private val args : ProductFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.text = args.uid.toString()
    }



    override fun getViewModel(): Class<ProductViewModel> = ProductViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProductBinding {
        return FragmentProductBinding.inflate(inflater, container, false)
    }

    override fun getFragmentRepository(): ProductRepository {
        val api = remoteDataSource.buildServiceApi(ProductApi::class.java)
        return ProductRepository(api)
    }
}


