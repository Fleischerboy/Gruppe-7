package org.example.kotlin.android.app.ui.home.profile

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
import org.example.kotlin.android.app.databinding.ProductCardProfileViewBinding
import org.example.kotlin.android.app.databinding.ProductCardViewBinding
import org.example.kotlin.android.app.ui.home.explore.ProductInterface

class UserProfileProductListAdapter() : ListAdapter<ProductResponse, UserProfileProductListAdapter.UserProfileProductViewHolder>(ProductComparator()){


    private lateinit var productInterface: ProductInterface

    fun setOnClickListener(productInterface: ProductInterface) {
        this.productInterface = productInterface

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserProfileProductViewHolder {
        val cardProduct = ProductCardProfileViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserProfileProductViewHolder(cardProduct)
    }

    override fun onBindViewHolder(holder: UserProfileProductViewHolder, position: Int) {
        val currentProduct = getItem(position)
        println(currentProduct)
        holder.bind(currentProduct)
    }


    inner class UserProfileProductViewHolder(private val binding: ProductCardProfileViewBinding) : RecyclerView.ViewHolder(binding.root) {

        private val productViewHolder = binding.userProductImageView

        fun bind(product: ProductResponse) {
            binding.apply {
                Glide.with(itemView).load(product.imageUrl).into(userProductImageView)
                tvProductTitle.text = product.title
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



