package org.example.kotlin.android.app.ui.home.explore

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.StateSet.TAG
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.example.kotlin.android.app.R
import org.example.kotlin.android.app.data.responses.ProductResponse
import org.example.kotlin.android.app.databinding.ProductCardViewBinding

class ProductListAdapter() : ListAdapter<ProductResponse, ProductListAdapter.ProductViewHolder>(ProductComparator()){


    private lateinit var productInterface: ProductInterface

    fun setOnClickListener(productInterface: ProductInterface) {
    this.productInterface = productInterface

    }


    // Called when there's a need for a new ViewHolder (a new item in the list/grid)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        // Inflates the movie_list_item.xml to a view for us
        val cardProduct = org.example.kotlin.android.app.databinding.ProductCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(cardProduct);
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentProduct = getItem(position)
        println(currentProduct)
        holder.bind(currentProduct)
    }


    inner class ProductViewHolder(private val binding: ProductCardViewBinding) : RecyclerView.ViewHolder(binding.root) {
        private val tvProductHeader: TextView = binding.tvProductHeader
        private val tvProductDescription: TextView = binding.tvProductDescription
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

