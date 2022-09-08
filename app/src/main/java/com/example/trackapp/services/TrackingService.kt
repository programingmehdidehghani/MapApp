package com.example.trackapp.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_LOW
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleService
import com.example.trackapp.other.Constants.ACTION_PAUSE_SERVICE
import com.example.trackapp.other.Constants.ACTION_START_OR_RESUME_SERVICE
import com.example.trackapp.other.Constants.ACTION_STOP_SERVICE
import com.example.trackapp.other.Constants.NOTIFICATION_CHANNEL_ID
import com.example.trackapp.other.Constants.NOTIFICATION_CHANNEL_NAME
import timber.log.Timber

class TrackingService : LifecycleService(){

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when(intent.action){
                ACTION_START_OR_RESUME_SERVICE -> {
                   Timber.d("started or resume service")
                }
                ACTION_PAUSE_SERVICE -> {
                    Timber.d("paused service")
                }
                ACTION_STOP_SERVICE -> {
                    Timber.d("stoped service")
                }
            }
        }


        return super.onStartCommand(intent, flags, startId)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel (notificationManager: NotificationManager){
          val channel = NotificationChannel(
              NOTIFICATION_CHANNEL_ID,
              NOTIFICATION_CHANNEL_NAME,
              IMPORTANCE_LOW
          )
        notificationManager.createNotificationChannel(channel)
    }

}