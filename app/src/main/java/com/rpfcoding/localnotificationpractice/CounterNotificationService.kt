package com.rpfcoding.localnotificationpractice

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class CounterNotificationService(
    private val context: Context
) {

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(counter: Int) {
        val activityIntent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            69,
            activityIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )
        val incrementAction = PendingIntent.getBroadcast(
            context,
            70,
            Intent(context, CounterNotificationReceiver::class.java),
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )
        val notification = NotificationCompat.Builder(context, COUNTER_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baby)
            .setContentTitle("Increment Counter")
            .setContentText("The count is $counter")
            .setContentIntent(pendingIntent)
            .addAction(
                R.drawable.ic_baby,
                "Increment",
                incrementAction
            )
            .build()

        notificationManager.notify(69, notification)
    }

    companion object {
        const val COUNTER_CHANNEL_ID = "counter_channel_id"
    }
}