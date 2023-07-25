package com.example.notification.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.notification.repository.MainRepository
import com.example.notification.NotificationService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {


    fun getNotificationService(context: Context): NotificationService {
        return mainRepository.getNotificationService(context)
    }
}