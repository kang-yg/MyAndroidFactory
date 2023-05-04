package com.example.studyandroid.view.Camera

import android.content.ContentValues
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.*
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import com.example.studyandroid.R
import java.util.*

class MyCamera(private val activity: AppCompatActivity, private val previewView: PreviewView) {
    private val cameraProviderFuture = ProcessCameraProvider.getInstance(activity)
    private var imageCapture: ImageCapture? = null
    private var videoCapture: VideoCapture<Recorder>? = null
    private var recording: Recording? = null

    fun startCamera() {
        val cameraProvider = cameraProviderFuture.get()
        cameraProviderFuture.addListener(
            {
                val preview = Preview.Builder().build().also { it.setSurfaceProvider(previewView.surfaceProvider) }
                imageCapture = ImageCapture.Builder().build()
                val recorder = Recorder.Builder().setQualitySelector(QualitySelector.from(Quality.HIGHEST)).build()
                videoCapture = VideoCapture.withOutput(recorder)

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(activity, cameraSelector, preview, imageCapture, videoCapture)
                } catch (e: Exception) {
                    Log.e("MyCamera", "${e.printStackTrace()}")
                }
            },
            ContextCompat.getMainExecutor(activity)
        )
    }

    fun takePhoto() {
        val name = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA).format(System.currentTimeMillis())
        val contentValue = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
        }

        val outputOptions = ImageCapture.OutputFileOptions.Builder(activity.contentResolver, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValue).build()
        imageCapture?.takePicture(
            outputOptions, ContextCompat.getMainExecutor(activity), object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val msg = "Photo capture succeeded: ${outputFileResults.savedUri}"
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e("MyCamera", "Photo capture failed: ${exception.message}", exception)
                }
            }
        )
    }

    fun takeVideo() {
        val curRecording = recording
        if (curRecording != null) {
            curRecording.stop()
            recording = null
            return
        }

        val name = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA).format(System.currentTimeMillis())
        val contentValue = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4")
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) put(MediaStore.Images.Media.RELATIVE_PATH, "Movies/CameraX-Video")
        }
        val mediaStoreOutputOptions = MediaStoreOutputOptions.Builder(activity.contentResolver, MediaStore.Video.Media.EXTERNAL_CONTENT_URI).setContentValues(contentValue).build()

        recording = videoCapture?.output?.prepareRecording(activity, mediaStoreOutputOptions).apply {
            if (PermissionChecker.checkSelfPermission(activity, android.Manifest.permission.RECORD_AUDIO) == PermissionChecker.PERMISSION_GRANTED) {
                this?.withAudioEnabled()
            }
        }?.start(ContextCompat.getMainExecutor(activity)) { recordEvent ->
            val btTakeVideo = activity.findViewById<Button>(R.id.btTakeVideo)
            when (recordEvent) {
                is VideoRecordEvent.Start -> {
                    btTakeVideo.text = "Recording..."
                }

                is VideoRecordEvent.Finalize -> {
                    if (!recordEvent.hasError()) {
                        val msg = "Video capture succeeded: " + "${recordEvent.outputResults.outputUri}"
                    } else {
                        recording?.close()
                        recording = null
                    }
                    btTakeVideo.text = "Start"
                }
            }
        }
    }
}