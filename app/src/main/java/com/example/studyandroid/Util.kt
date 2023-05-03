package com.example.studyandroid

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Point
import android.util.TypedValue
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment

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

    fun requestPermissionLauncher(activity: ComponentActivity? = null, fragment: Fragment? = null): ActivityResultLauncher<Array<String>>? {
        return activity?.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->

        } ?: fragment?.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->

        }
    }

    fun checkPermission(activity: Activity, permissions: Array<String>): Boolean {
        if (permissions.none { ActivityCompat.shouldShowRequestPermissionRationale(activity, it) })
            return true

        permissions.forEach {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, it)) {
                showPermissionAlert(activity, it)
            }
        }
        return false
    }

    private fun showPermissionAlert(context: Context, permissionName: String): AlertDialog {
        return AlertDialog.Builder(context)
            .setTitle("Alert")
            .setMessage("$permissionName 권한이 필요 합니다.")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
    }
}