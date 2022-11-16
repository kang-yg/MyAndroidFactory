package com.example.studyandroid.view.Navigation

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.studyandroid.databinding.FragmentNavigationDialogBinding
import com.example.studyandroid.view.BaseDialogFragment

class FragmentNavigationDialog : BaseDialogFragment<FragmentNavigationDialogBinding>(FragmentNavigationDialogBinding::inflate) {
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDialogSizeRatio(0.9f)

        navController = findNavController()
        binding?.let {
            it.btBackStackEntry.setOnClickListener { _ ->
                val text = it.etPreviousBackStackEntry.text.toString()
                navController.previousBackStackEntry?.savedStateHandle?.set("text", text)
                this.dismiss()
            }
        }
    }

    /**
     * This code is not necessary.
     */
//    fun showDialog() {
//        val fragmentTransaction = parentFragmentManager.beginTransaction()
//        val prevDialog = parentFragmentManager.findFragmentByTag(fragmentNavigationDialogTag)
//        if (prevDialog != null) {
//            fragmentTransaction.remove(prevDialog)
//        }
//        fragmentTransaction.addToBackStack(null)
//        newInstance().show(fragmentTransaction, fragmentNavigationDialogTag)
//    }

    companion object {
        /**
         * This code is not necessary.
         */
//        const val fragmentNavigationDialogTag = "FragmentNavigationDialog"

        /**
         * This code is not necessary.
         */
//        fun newInstance(): FragmentNavigationDialog = FragmentNavigationDialog()
    }
}