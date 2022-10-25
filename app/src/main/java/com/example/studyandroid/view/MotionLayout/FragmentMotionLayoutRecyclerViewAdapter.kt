package com.example.studyandroid.view.MotionLayout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.studyandroid.databinding.FragmentMotionlayoutItemBinding

class FragmentMotionLayoutRecyclerViewAdapter(private val callback: (FragmentMotionLayoutRecyclerViewItem) -> Unit) :
    ListAdapter<FragmentMotionLayoutRecyclerViewItem, FragmentMotionLayoutRecyclerViewAdapter.FragmentMotionLayoutRecyclerViewItemViewHolder>(diffUtil) {
    inner class FragmentMotionLayoutRecyclerViewItemViewHolder(private val itemViewBinding: FragmentMotionlayoutItemBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {

        fun bind(index: Int) {
            with(itemViewBinding.ivFragmentMotionLayout) {
                Glide.with(context).load(currentList[index].src).centerCrop().into(this)
                setOnClickListener {
                    callback(currentList[index])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FragmentMotionLayoutRecyclerViewItemViewHolder {
        return FragmentMotionLayoutRecyclerViewItemViewHolder(
            FragmentMotionlayoutItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FragmentMotionLayoutRecyclerViewItemViewHolder, position: Int) {
        holder.bind(position)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<FragmentMotionLayoutRecyclerViewItem>() {
            override fun areItemsTheSame(oldItem: FragmentMotionLayoutRecyclerViewItem, newItem: FragmentMotionLayoutRecyclerViewItem): Boolean {
                return oldItem.src == newItem.src
            }

            override fun areContentsTheSame(oldItem: FragmentMotionLayoutRecyclerViewItem, newItem: FragmentMotionLayoutRecyclerViewItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}