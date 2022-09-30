package com.example.studyandroid

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.studyandroid.Util.getStatusBarHeight
import com.example.studyandroid.databinding.FragmentNavigationBinding

class FragmentNavigation : BaseFragment<FragmentNavigationBinding>(FragmentNavigationBinding::inflate) {
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
        binding?.let {
            // StatusBar 높이만큼 패딩 적용.
            it.clFragNavigation.setPadding(0, requireActivity().getStatusBarHeight(), 0, requireActivity().getStatusBarHeight())

            navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("text")?.observe(viewLifecycleOwner) { str ->
                it.tvCurrentBackStack.text = str
            }

            it.btGoFragmentNavigationSub.setOnClickListener {
                val action = FragmentNavigationDirections.actionFragmentNavigationToFragmentNavigationSub("Apple")
                navController.navigate(action)
            }

            it.btGoFragmentNavigationDialog.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_fragmentNavigation_to_fragmentNavigationDialog, null))
        }
    }
}