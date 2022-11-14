package org.example.kotlin.android.app.ui.home.inbox.bid_system.bids

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.example.kotlin.android.app.data.responses.ProductResponse
import org.example.kotlin.android.app.databinding.ProductCardViewBinding
import org.example.kotlin.android.app.ui.home.explore.ProductInterface

class ProductsWithBidListAdapter() : ListAdapter<ProductResponse, ProductsWithBidListAdapter.ProductsWithBidViewHolder>(ProductComparator()) {

    private lateinit var productInterface: ProductInterface

    fun setOnClickListener(productInterface: ProductInterface) {
        this.productInterface = productInterface

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsWithBidViewHolder {
        val cardProduct = ProductCardViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ProductsWithBidViewHolder(cardProduct);
    }

    override fun onBindViewHolder(holder: ProductsWithBidViewHolder, position: Int) {
        val currentProduct = getItem(position)
        holder.bind(currentProduct)
    }

    inner class ProductsWithBidViewHolder(private val binding: ProductCardViewBinding) : RecyclerView.ViewHolder(binding.root) {
        private val productViewHolder = binding.productCardView



        fun bind(product: ProductResponse) {
            binding.apply {
                Glide.with(itemView).load(product.imageUrl).into(productImgItem)
                tvProductHeader.text = product.title
                tvProductDescription.text = product.description
                productViewHolder.setOnClickListener {
                    productInterface.onItemClickListener(product);
                }
            }


        }

    }

}

class ProductComparator : DiffUtil.ItemCallback<ProductResponse>() {

    override fun areItemsTheSame(oldItem: ProductResponse, newItem: ProductResponse) =
        oldItem.title == newItem.title

    override fun areContentsTheSame(
        oldItem: ProductResponse,
        newItem: ProductResponse
    ) = oldItem == newItem

}