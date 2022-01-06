package com.example.hotspot.other.service
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_LOW
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import com.example.hotspot.R
import com.example.hotspot.other.Constants.ACTION_PAUSE_SERVICE
import com.example.hotspot.other.Constants.ACTION_START_OR_RESUME_SERVICE
import com.example.hotspot.other.Constants.ACTION_STOPPED_SERVICE
import com.example.hotspot.other.Constants.NOTIFICATION_CHANNEL_ID
import com.example.hotspot.other.Constants.NOTIFICATION_CHANNEL_NAME
import com.example.hotspot.view.AfterLoginActivity

class TrackingService : LifecycleService() {


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {

                ACTION_START_OR_RESUME_SERVICE -> {
                    Log.i(TAG,"Started or resumed service ")
                }

                ACTION_PAUSE_SERVICE -> {
                    Log.i(TAG,"Pause service ")
                }

                ACTION_STOPPED_SERVICE -> {
                    Log.i(TAG,"Stopped service ")
                }

                else -> {}
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }




    private fun startForegroundService() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager)
        }

        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setAutoCancel(false)
            .setOngoing(true)
            .setSmallIcon(R.drawable.direction_run_black)
            .setContentTitle("Running App")
            .setContentText("00:00:00")
    }




//
//    private fun getMainActivityPendingIntent() = PendingIntent.getActivity(
//        this,
//        0,
//        Intent(this, AfterLoginActivity)
//
//
//    )




    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            IMPORTANCE_LOW
        )

        notificationManager.createNotificationChannel(channel)
    }

}