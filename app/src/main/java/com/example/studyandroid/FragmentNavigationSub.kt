package com.example.studyandroid

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.studyandroid.databinding.FragmentNavigationSubBinding

class FragmentNavigationSub : BaseFragment<FragmentNavigationSubBinding>(FragmentNavigationSubBinding::inflate) {
    private val args by navArgs<FragmentNavigationSubArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            // StatusBar 높이만큼 패딩 적용.
            it.clFragNavigationSub.setPadding(0, getStatusBarHeight(), 0, getStatusBarHeight())

            it.tvFragmentNavigationSubText.text = args.text
        }
    }
}