package com.example.studyandroid

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Point
import android.net.Uri
import android.provider.Settings
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
        return activity?.let {
            it.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                handlePermissionRequestResult(it, permissions)
            }
        } ?: fragment?.let {
            it.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                handlePermissionRequestResult(it.requireActivity(), permissions)
            }
        }
    }

    fun checkPermission(activity: Activity, permissions: Array<String>): Boolean {
        if (permissions.all { permission -> activity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED }) {
            return true
        }
        return false
    }

    private fun handlePermissionRequestResult(activity: ComponentActivity, permissions: Map<String, Boolean>) {
        permissions.entries.filter {
            // Check permanently denied permission
            it.value == ActivityCompat.shouldShowRequestPermissionRationale(activity, it.key)
        }.forEach { permission ->
            showPermissionAlert(activity, permission.key).show()
        }
    }

    private fun showPermissionAlert(context: Context, permissionName: String): AlertDialog {
        return AlertDialog.Builder(context)
            .setTitle("Alert")
            .setMessage("$permissionName 권한이 필요 합니다.")
            .setPositiveButton("OK") { dialog, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", context.packageName, null)
                }
                dialog.dismiss()
                context.startActivity(intent)
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
            .create()
    }
}