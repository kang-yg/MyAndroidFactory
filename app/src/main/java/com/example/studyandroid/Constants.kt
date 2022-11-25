package com.example.studyandroid

import android.Manifest
import android.os.Build
import android.os.Build.VERSION_CODES.Q
import android.os.Build.VERSION_CODES.TIRAMISU

object Constants {
    const val BASE_URL = "https://run.mocky.io"
    const val LOREM_BASE_URL = "https://picsum.photos"
    const val NOTIFICATION_CHANNEL_ID = "StudyAndroid"
    const val NOTIFICATION_CHANNEL_NAME = "StudyAndroid Notification"
    const val NOTIFICATION_NOTIFICATION_ID = 999
    const val NOTIFICATION_TITLE = "Notification title"
    const val NOTIFICATION_BODY = "Notification body"

    private val lessThanQ = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private val higherThanQ = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    private val higherThanTIRAMISU = arrayOf(Manifest.permission.READ_MEDIA_IMAGES, Manifest.permission.READ_MEDIA_VIDEO)
    val requiredPermissions = if (Build.VERSION.SDK_INT >= TIRAMISU) higherThanTIRAMISU else if (Build.VERSION.SDK_INT >= Q) higherThanQ else lessThanQ
}