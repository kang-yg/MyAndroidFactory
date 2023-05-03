package com.example.studyandroid.view

import android.os.Build
import android.view.WindowManager
import androidx.core.view.WindowCompat
import com.example.studyandroid.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun initView() {
        setStatusBarTransparent()
    }

    /**
     * Make status bar transparent.
     */
    private fun setStatusBarTransparent() {
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
            WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}