package com.example.studyandroid.view.DataStore

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.studyandroid.Util.dpToPx
import com.example.studyandroid.Util.getStatusBarHeight
import com.example.studyandroid.databinding.FragmentDatastoreBinding
import com.example.studyandroid.view.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDataStore : BaseFragment<FragmentDatastoreBinding>(FragmentDatastoreBinding::inflate) {
    private val fragmentDataStoreViewModel: FragmentDataStoreViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            // StatusBar 높이만큼 패딩 적용.
            it.clFragmentDataStore.setPadding(0, requireActivity().getStatusBarHeight(), 0, requireActivity().dpToPx(8))

            fragmentDataStoreViewModel.myNumberLiveData.observe(viewLifecycleOwner) { myNumber ->
                it.tvPreferenceDataStoreMyNumber.text = myNumber.toString()
            }

            it.btIncreaseMyNumber.setOnClickListener {
                fragmentDataStoreViewModel.increaseMyNumber()
            }
        }
    }
}