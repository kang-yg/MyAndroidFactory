package com.example.studyandroid.view.Paging

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.studyandroid.Util.dpToPx
import com.example.studyandroid.Util.getStatusBarHeight
import com.example.studyandroid.databinding.FragmentPagingBinding
import com.example.studyandroid.view.BaseFragment

class FragmentPaging : BaseFragment<FragmentPagingBinding>(FragmentPagingBinding::inflate) {
    val fragmentPagingViewModel: FragmentPagingViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            // StatusBar 높이만큼 패딩 적용.
            it.clFragPaging.setPadding(0, requireActivity().getStatusBarHeight(), 0, requireActivity().dpToPx(8))


        }
    }
}