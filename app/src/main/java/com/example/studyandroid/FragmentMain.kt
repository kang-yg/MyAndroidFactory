package com.example.studyandroid

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.studyandroid.databinding.FragmentMainBinding

class FragmentMain : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        binding?.let {
            // StatusBar 높이만큼 패딩 적용.
            it.clFragMain.setPadding(0, getStatusBarHeight(), 0, 0)

/*
            // Lambda 를 사용하지 않고 destination 이동.
            it.btGoFragmentCoordinatorLayout.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_fragmentMain_to_fragmentCoordinatorLayout, null))
*/

            // Lambda 를 사용한 destination 이동.
            it.btGoFragmentCoordinatorLayout.setOnClickListener {
                navController.navigate(R.id.action_fragmentMain_to_fragmentCoordinatorLayout)
            }

            it.btGoFragmentMotionLayout.setOnClickListener {
                navController.navigate(R.id.action_fragmentMain_to_fragmentMotionLayout)
            }

            it.btGoFragmentNavigation.setOnClickListener {
                navController.navigate(R.id.action_fragmentMain_to_fragmentNavigation)
            }
        }
    }
}