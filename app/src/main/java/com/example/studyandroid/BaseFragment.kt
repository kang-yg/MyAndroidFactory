package com.example.studyandroid

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(private val inflate: Inflate<VB>) : Fragment() {
    private var _binding: VB? = null
    val binding
        get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Get status bar height.
     */
    protected fun getStatusBarHeight(): Int {
        val statusBarHeight = resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (statusBarHeight > 0) resources.getDimensionPixelSize(statusBarHeight) else 0
    }

    protected fun Int.dpToPx(): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), requireActivity().resources.displayMetrics)
    }

    protected fun Int.pxToDp(): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, this.toFloat(), requireActivity().resources.displayMetrics)
    }
}