package com.example.studyandroid.view.MotionLayout

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.studyandroid.Util.getStatusBarHeight
import com.example.studyandroid.databinding.FragmentMotionlayoutBinding
import com.example.studyandroid.model.Status
import com.example.studyandroid.view.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentMotionLayout : BaseFragment<FragmentMotionlayoutBinding>(FragmentMotionlayoutBinding::inflate) {
    private lateinit var navController: NavController

    private val viewModel: FragmentMotionLayoutViewModel by viewModels()
    private lateinit var fragmentMotionLayoutRecyclerViewAdapter: FragmentMotionLayoutRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
        binding?.let {
            // StatusBar 높이만큼 패딩 적용.
            it.mlFragMotion.setPadding(0, requireActivity().getStatusBarHeight(), 0, requireActivity().getStatusBarHeight())

            initRvFragmentMotionLayout()
            observeHeroesItem()
        }
    }

    private fun initRvFragmentMotionLayout() {
        fragmentMotionLayoutRecyclerViewAdapter = FragmentMotionLayoutRecyclerViewAdapter {
            val action = FragmentMotionLayoutDirections.actionFragmentMotionLayoutToFragmentMotionLayoutDetail(it)
            navController.navigate(action)
        }
        binding!!.rvFragmentMotionLayout.adapter = fragmentMotionLayoutRecyclerViewAdapter
    }

    private fun observeHeroesItem() {
        viewModel.res.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data.let { res ->
                        res?.data.let { heroes ->
                            fragmentMotionLayoutRecyclerViewAdapter.submitList(heroes)
                        }
                    }
                }

                Status.LOADING -> {

                }

                Status.ERROR -> {
                    Snackbar.make(binding!!.root, "Error", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}