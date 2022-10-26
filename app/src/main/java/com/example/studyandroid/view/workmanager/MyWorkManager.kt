package com.example.studyandroid.view.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.studyandroid.Constants
import com.example.studyandroid.MyNotificationManager

class MyWorkManager(private val context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {
    override fun doWork(): Result {
        val title = inputData.getString(Constants.NOTIFICATION_TITLE).toString()
        val message = inputData.getString(Constants.NOTIFICATION_BODY).toString()

        MyNotificationManager(
            context,
            Constants.NOTIFICATION_CHANNEL_ID,
            Constants.NOTIFICATION_CHANNEL_NAME,
            Constants.NOTIFICATION_NOTIFICATION_ID,
            title,
            message
        )

        return Result.success()
    }
}