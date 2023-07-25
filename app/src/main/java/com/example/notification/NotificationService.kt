package com.example.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.notification.activity.MainActivity
import com.example.notification.broadcasts.MyReceiver
import com.example.notification.constants.ActionNotificationService
import javax.inject.Inject


class NotificationService @Inject constructor(val context: Context) {
    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun createNotification(channelDescription : String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                ActionNotificationService.CHANNEL_ID,
                "action",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = channelDescription


            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showBasicNotification(title : String , content : String) {
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )
        val notification = NotificationCompat.Builder(context, ActionNotificationService.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_message)
            .setContentTitle(title)
            .setContentText(content)
            .setContentIntent(activityPendingIntent)
            .build()
        notificationManager.notify(1,notification)
    }
    fun showActionNotification() {
        val activityIntent = Intent(context, MyReceiver::class.java).apply {
            putExtra("message","stop")
        }
        val receiverPendingIntent = PendingIntent.getBroadcast(
            context,
            1,
            activityIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )
        val notification = NotificationCompat.Builder(context, ActionNotificationService.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_message)
            .setContentTitle("Phn E-Learning")
            .setContentText("your song is playing...")
            .addAction(0,"Stop",receiverPendingIntent)
            .build()
        notificationManager.notify(1,notification)
    }
}