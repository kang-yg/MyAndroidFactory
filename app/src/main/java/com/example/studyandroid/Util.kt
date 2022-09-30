package com.example.studyandroid

import android.content.Context
import android.graphics.Point
import android.util.TypedValue

object Util {
    /**
     * Get status bar height.
     */
    fun Context.getStatusBarHeight(): Int {
        val statusBarHeight = resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (statusBarHeight > 0) resources.getDimensionPixelSize(statusBarHeight) else 0
    }

    fun Context.dpToPx(scale: Int): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, scale.toFloat(), resources.displayMetrics).toInt()

    fun Context.pxToDp(scale: Int): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, scale.toFloat(), resources.displayMetrics).toInt()

    fun Context.getDeviceDisplayMetricsPoint(): Point = Point(resources.displayMetrics.widthPixels, resources.displayMetrics.heightPixels)
}