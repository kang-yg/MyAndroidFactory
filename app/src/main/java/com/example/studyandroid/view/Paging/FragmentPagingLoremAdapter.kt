package com.example.studyandroid.view.Paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.studyandroid.Util.dpToPx
import com.example.studyandroid.databinding.FragmentPagingLoremItemBinding
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

class FragmentPagingLoremAdapter : PagingDataAdapter<LoremImageInfo, FragmentPagingLoremAdapter.FragmentPagingLoremViewHolder>(diffUtil) {
    class FragmentPagingLoremViewHolder(private val viewBinding: FragmentPagingLoremItemBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        private val shimmerDrawable = ShimmerDrawable().apply {
            setShimmer(
                Shimmer.AlphaHighlightBuilder()
                    .setDuration(1500)
                    .setBaseAlpha(0.8f)
                    .setHighlightAlpha(0.4f)
                    .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                    .setAutoStart(true)
                    .build()
            )
        }

        fun bind(loremImageInfo: LoremImageInfo) {
            with(viewBinding) {
                val context = root.context
                Glide.with(context).load(loremImageInfo.downloadUrl).placeholder(shimmerDrawable).override(context.dpToPx(250), context.dpToPx(200)).centerCrop().into(ivLorem)
                tvLoremAuthor.text = loremImageInfo.author
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FragmentPagingLoremViewHolder {
        return FragmentPagingLoremViewHolder(FragmentPagingLoremItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FragmentPagingLoremViewHolder, position: Int) {
        holder.bind(getItem(position) ?: return)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<LoremImageInfo>() {
            override fun areItemsTheSame(oldItem: LoremImageInfo, newItem: LoremImageInfo): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: LoremImageInfo, newItem: LoremImageInfo): Boolean = oldItem == newItem
        }
    }
}