# AK-Notification
Android kotlin Notification


# MainActivtiy.kt

```
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
  
```

# Result

<img src="https://github.com/lihancode/AK-Notification/blob/master/Screenshot_1568620003.png" hieght=600 width=200></img>
<img src="https://github.com/lihancode/AK-Notification/blob/master/Screenshot_1568620017.png" hieght=600 width=200></img>
<img src="https://github.com/lihancode/AK-Notification/blob/master/Screenshot_1568620031.png" hieght=600 width=200></img>

