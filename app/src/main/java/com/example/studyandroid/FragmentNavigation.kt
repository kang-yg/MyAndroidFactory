package com.example.studyandroid

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.studyandroid.databinding.FragmentNavigationBinding

class FragmentNavigation : BaseFragment<FragmentNavigationBinding>(FragmentNavigationBinding::inflate) {
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        binding?.let {
            // StatusBar 높이만큼 패딩 적용.
            it.clFragNavigation.setPadding(0, getStatusBarHeight(), 0, getStatusBarHeight())

            it.btGoFragmentNavigationSub.setOnClickListener {
                val action = FragmentNavigationDirections.actionFragmentNavigationToFragmentNavigationSub("Apple")
                navController.navigate(action)
            }
        }
    }
}