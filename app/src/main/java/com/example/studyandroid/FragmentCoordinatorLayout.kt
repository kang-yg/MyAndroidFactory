package com.example.studyandroid

import android.os.Bundle
import android.view.View
import com.example.studyandroid.databinding.FragmentCoordinatorlayoutBinding

class FragmentCoordinatorLayout : BaseFragment<FragmentCoordinatorlayoutBinding>(FragmentCoordinatorlayoutBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            // StatusBar 높이만큼 패딩 적용.
            it.tbFragCoordinatorLayout.setPadding(0, getStatusBarHeight(), 0, 8.dpToPx().toInt())
        }
    }
}