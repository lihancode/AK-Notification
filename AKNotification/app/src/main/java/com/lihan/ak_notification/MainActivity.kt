package com.lihan.ak_notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*
import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log


class MainActivity : AppCompatActivity() {

    private lateinit var  notificationBuild : Notification
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sendNotification("5566",packageName)
        sendButton.setOnClickListener {
            sendNotification("12345","My Cars notify")
        }


    }


    private fun sendNotification(id : String , channelName: String) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //pending Intent
        val requestID = System.currentTimeMillis().toInt()
        val notificationIntent = Intent(applicationContext, this::class.java)
        notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        val contentIntent = PendingIntent.getActivity(
            this,
            requestID,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        //check version
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //create Channel
            val important = NotificationManager.IMPORTANCE_LOW
            var notificationChannel = NotificationChannel(id,channelName,important)
            notificationChannel.enableVibration(true)
            notificationChannel.enableLights(true)
            notificationChannel.description = "This is a notification"
            notificationManager.createNotificationChannel(notificationChannel)

            //build notification with channel
            notificationBuild = NotificationCompat.Builder(this,id)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("This is a title")
                .setContentText("content abcdefg")
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
                .build()
        }else{
            //build notification without channel
            notificationBuild = NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("This is a title")
                .setContentText("content abcdefg")
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
                .build()
        }
        notificationManager.notify(id.toInt(),notificationBuild)
    }
}
