package com.example.studyandroid.view.MotionLayout

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.studyandroid.Util.getStatusBarHeight
import com.example.studyandroid.databinding.FragmentMotionlayoutDetailBinding
import com.example.studyandroid.view.BaseFragment

class FragmentMotionLayoutDetail : BaseFragment<FragmentMotionlayoutDetailBinding>(FragmentMotionlayoutDetailBinding::inflate) {
    private val args by navArgs<FragmentMotionLayoutDetailArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            // StatusBar 높이만큼 패딩 적용.
            it.mlFragMotionDetail.setPadding(0, requireActivity().getStatusBarHeight(), 0, requireActivity().getStatusBarHeight())

            val motionLayoutRecyclerViewItem = args.motionLayoutRecyclerViewItem
            initIvThumbnail(motionLayoutRecyclerViewItem)
            initTvTitle(motionLayoutRecyclerViewItem)
        }
    }

    private fun initIvThumbnail(motionLayoutRecyclerViewItem: FragmentMotionLayoutRecyclerViewItem) {
        with(binding!!.ivThumbnail.ivFragmentMotionLayout) {
            Glide.with(context).load(motionLayoutRecyclerViewItem.src).centerCrop().into(this)
        }
    }

    private fun initTvTitle(motionLayoutRecyclerViewItem: FragmentMotionLayoutRecyclerViewItem) {
        binding!!.tvTitle.text = motionLayoutRecyclerViewItem.name
    }
}