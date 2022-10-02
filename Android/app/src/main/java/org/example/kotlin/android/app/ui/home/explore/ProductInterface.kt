package org.example.kotlin.android.app.ui.home.explore

import org.example.kotlin.android.app.data.responses.ProductResponse

interface ProductInterface {

    fun onItemClickListener(product: ProductResponse)
}