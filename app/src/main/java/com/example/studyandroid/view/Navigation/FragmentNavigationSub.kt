package com.example.studyandroid.view.Navigation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.studyandroid.Util.getStatusBarHeight
import com.example.studyandroid.databinding.FragmentNavigationSubBinding
import com.example.studyandroid.view.BaseFragment

class FragmentNavigationSub : BaseFragment<FragmentNavigationSubBinding>(FragmentNavigationSubBinding::inflate) {
    private val args by navArgs<FragmentNavigationSubArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            // StatusBar 높이만큼 패딩 적용.
            it.clFragNavigationSub.setPadding(0, requireActivity().getStatusBarHeight(), 0, requireActivity().getStatusBarHeight())

            it.tvFragmentNavigationSubText.text = args.text
        }
    }
}