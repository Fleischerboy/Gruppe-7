package org.example.kotlin.android.app.ui.home.inbox.bid_system.bid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.example.kotlin.android.app.data.responses.BidResponse
import org.example.kotlin.android.app.databinding.BidCardBinding

class BidListAdapter() : ListAdapter<BidResponse, BidListAdapter.BidViewHolder>(BidComparator()) {

    private lateinit var bidInterface: BidInterface


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BidViewHolder {
        val cardBid = BidCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return BidViewHolder(cardBid)
    }

    override fun onBindViewHolder(holder: BidViewHolder, position: Int) {
        val currentBid = getItem(position)
        holder.bind(currentBid)
    }

    inner class BidViewHolder(private val binding: BidCardBinding) : RecyclerView.ViewHolder(binding.root) {
        private val bidViewHolder = binding.bidCardView

        fun bind(bid : BidResponse) {
            binding.apply {
                bidAmountTextView.text = bid.bidAmount.toString()
//                acceptBidButton.setOnClickListener {
//                    bidInterface.onItemClickListener(bid)
//                }
            }
        }
    }
}



class BidComparator : DiffUtil.ItemCallback<BidResponse>() {

    override fun areItemsTheSame(oldItem: BidResponse, newItem: BidResponse) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: BidResponse,
        newItem: BidResponse
    ) = oldItem == newItem

}