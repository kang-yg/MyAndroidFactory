package com.example.studyandroid.view.CustomView

import android.os.Bundle
import android.view.View
import com.example.studyandroid.Util.getStatusBarHeight
import com.example.studyandroid.databinding.FragmentCustomviewBinding
import com.example.studyandroid.view.BaseFragment

class FragmentCustomView : BaseFragment<FragmentCustomviewBinding>(FragmentCustomviewBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            // StatusBar 높이만큼 패딩 적용.
            it.llFragmentCustomView.setPadding(0, requireActivity().getStatusBarHeight(), 0, requireActivity().getStatusBarHeight())

            it.ctv.setOnClickListener { view ->
                (view as CircleBorderTextView).setText("Hello")
            }
        }
    }
}