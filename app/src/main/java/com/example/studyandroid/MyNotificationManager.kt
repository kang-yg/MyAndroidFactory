package com.example.studyandroid

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.studyandroid.view.MainActivity

class MyNotificationManager(
    private val context: Context,
    private val channelID: String,
    private val channelName: String,
    private val notificationID: Int,
    private val notificationTitle: String,
    private val notificationMessage: String
) {
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT).apply {
                enableLights(true)
                lightColor = Color.BLUE
                enableVibration(true)
            }.also {
                notificationManager.createNotificationChannel(it)
            }
        }
    }

    private fun createNotification() {
        val notificationIntent = Intent(context, MainActivity::class.java).apply {
            action = Intent.ACTION_MAIN
            addCategory(Intent.CATEGORY_LAUNCHER)
        }
        val notificationPendingIntent = PendingIntent.getActivity(context, notificationID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notificationBuilder = NotificationCompat.Builder(context, channelID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(notificationTitle)
            .setContentText(notificationMessage)
            .setContentIntent(notificationPendingIntent).build()

        notificationManager.notify(notificationID, notificationBuilder)
    }

    init {
        createNotificationChannel()
        createNotification()
    }
}