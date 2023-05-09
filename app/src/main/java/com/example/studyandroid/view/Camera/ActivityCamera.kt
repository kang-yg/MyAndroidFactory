package com.example.studyandroid.view.Camera

import android.os.Bundle
import com.example.studyandroid.Constants.cameraPermissions
import com.example.studyandroid.Util.checkPermission
import com.example.studyandroid.Util.requestPermissionLauncher
import com.example.studyandroid.databinding.ActivityCameraBinding
import com.example.studyandroid.view.BaseActivity

class ActivityCamera : BaseActivity<ActivityCameraBinding>() {
    var myCamera: MyCamera? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissionLauncher(activity = this)?.launch(cameraPermissions)
    }

    override fun getViewBinding(): ActivityCameraBinding = ActivityCameraBinding.inflate(layoutInflater)

    override fun initView() {
        binding?.let {
            myCamera = MyCamera(this@ActivityCamera, it.cameraPreview)
            it.initBtTakePhoto()
            it.initBtTakeVideo()
        }
    }

    override fun onResume() {
        super.onResume()
        if (checkPermission(this, cameraPermissions)) myCamera?.startCamera()
    }

    private fun ActivityCameraBinding.initBtTakePhoto() {
        btTakePhoto.setOnClickListener {
            myCamera?.takePhoto()
        }
    }

    private fun ActivityCameraBinding.initBtTakeVideo() {
        btTakeVideo.setOnClickListener {
            myCamera?.takeVideo()
        }
    }
}