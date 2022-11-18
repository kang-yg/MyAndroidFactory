package com.example.studyandroid.view.Paging

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.studyandroid.Util.dpToPx
import com.example.studyandroid.Util.getStatusBarHeight
import com.example.studyandroid.databinding.FragmentPagingBinding
import com.example.studyandroid.view.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentPaging : BaseFragment<FragmentPagingBinding>(FragmentPagingBinding::inflate) {
    private val fragmentPagingViewModel: FragmentPagingViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            // StatusBar 높이만큼 패딩 적용.
            it.clFragPaging.setPadding(0, requireActivity().getStatusBarHeight(), 0, requireActivity().dpToPx(8))

            initRvFragmentPaging()
        }
    }

    private fun initRvFragmentPaging() {
        val fragmentPagingLoremAdapter = FragmentPagingLoremAdapter()
        lifecycleScope.launch {
            fragmentPagingViewModel.getLoremImages(20).collect {
                fragmentPagingLoremAdapter.submitData(it)
            }
        }
        binding!!.rvFragmentPaging.adapter = fragmentPagingLoremAdapter
    }
}