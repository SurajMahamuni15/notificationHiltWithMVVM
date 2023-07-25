package com.example.notification.repository

import android.content.Context
import com.example.notification.NotificationModule
import com.example.notification.NotificationService
import javax.inject.Inject

class MainRepository @Inject constructor(private val notificationModule: NotificationModule,) {
    fun getNotificationService(context: Context) : NotificationService {
        return notificationModule.createNotification(context)
    }
}