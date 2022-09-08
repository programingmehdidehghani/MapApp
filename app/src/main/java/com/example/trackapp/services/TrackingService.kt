package com.example.trackapp.services

import android.content.Intent
import androidx.lifecycle.LifecycleService
import com.example.trackapp.other.Constants.ACTION_START_OR_RESUME_SERVICE
import timber.log.Timber

class TrackingService : LifecycleService(){

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when(intent.action){
                ACTION_START_OR_RESUME_SERVICE -> {
                   Timber.d("started or resume service")
                }
            }
        }


        return super.onStartCommand(intent, flags, startId)
    }

}