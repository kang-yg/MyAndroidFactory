package com.example.studyandroid.view.storage

import android.os.Bundle
import android.view.View
import com.example.studyandroid.Constants
import com.example.studyandroid.Util.dpToPx
import com.example.studyandroid.Util.getStatusBarHeight
import com.example.studyandroid.databinding.FragmentStorageBinding
import com.example.studyandroid.view.BaseFragment

class FragmentStorage : BaseFragment<FragmentStorageBinding>(FragmentStorageBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // MediaStore Android SDK level에 따른 Permission request
        requestPermissionLauncher().launch(Constants.requiredPermissions)

        binding?.let {
            // StatusBar 높이만큼 패딩 적용.
            it.clFragmentStorage.setPadding(0, requireActivity().getStatusBarHeight(), 0, requireActivity().dpToPx(8))


        }
    }

    private fun initBtMediaStore() {

    }

    private fun initBtStorageAccessFramework() {

    }
}