package com.example.notification.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.notification.databinding.ActivityMainBinding
import com.example.notification.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initilizeListener()
    }

    private fun initilizeListener() {
        val notificationService = viewModel.getNotificationService(this)
        notificationService.createNotification("Used to Action Notification")
        binding.getNotification.setOnClickListener {
            notificationService.showBasicNotification(
                binding.edtTitle.text.toString(),
                binding.edtContent.text.toString()
            )
        }
        binding.getActionNotification.setOnClickListener {
            notificationService.showActionNotification()
        }
    }


}