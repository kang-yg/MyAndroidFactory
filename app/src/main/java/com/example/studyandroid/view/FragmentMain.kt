package com.example.studyandroid.view

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.studyandroid.R
import com.example.studyandroid.Util.getStatusBarHeight
import com.example.studyandroid.databinding.FragmentMainBinding

class FragmentMain : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
        binding?.let {
            // StatusBar 높이만큼 패딩 적용.
            it.clFragMain.setPadding(0, requireActivity().getStatusBarHeight(), 0, 0)

/*
            // Lambda 를 사용하지 않고 destination 이동.
            it.btGoFragmentCoordinatorLayout.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_fragmentMain_to_fragmentCoordinatorLayout, null))
*/

            // Lambda 를 사용한 destination 이동.
            it.btGoFragmentCoordinatorLayout.setOnClickListener {
                navController.navigate(R.id.action_fragmentMain_to_fragmentCoordinatorLayout)
            }

            it.btGoFragmentMotionLayout.setOnClickListener {
                navController.navigate(R.id.action_fragmentMain_to_navigation_motionlayout_graph)
            }

            it.btGoFragmentNavigation.setOnClickListener {
                navController.navigate(R.id.action_fragmentMain_to_fragmentNavigation)
            }

            it.btGoFragmentWorkManager.setOnClickListener {
                navController.navigate(R.id.action_fragmentMain_to_fragmentWorkManager)
            }

            it.btGoFragmentCustomView.setOnClickListener {
                navController.navigate(R.id.action_fragmentMain_to_fragmentCustomView)
            }
        }
    }
}