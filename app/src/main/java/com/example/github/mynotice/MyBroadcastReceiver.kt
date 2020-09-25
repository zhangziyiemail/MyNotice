package com.example.github.mynotice

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder

/**
 *   Created by zhangziyi on 9/24/20 19:12
 */
private const val TAG = "MyBroadcastReceiver"
private const val NOTIFICATIONID = 1

var NOTIFICATKIONCALLBACK = "com.example.broadcast.MY_NOTIFICATION_CALLBSCK"


class MyBroadcastReceiver : BroadcastReceiver() {
    val snoozePendingIntent: PendingIntent =
        PendingIntent.getBroadcast(MyApplication.instance, 0, snoozeIntent(), 0)

    override fun onReceive(context: Context, intent: Intent) {
        with(NotificationManagerCompat.from(MyApplication.instance)) {
            when (intent.getStringExtra("type")) {
                "notfied" -> StringBuilder().apply {
                    var builder = NotificationCompat.Builder(MyApplication.instance, TAG)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(intent.getStringExtra("title"))
                        .setContentText(intent.getStringExtra("data"))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(resultIntent())
                        .addAction(
                            R.mipmap.ic_launcher, "click see detail",
                            snoozePendingIntent
                        ).build()

                    notify(NOTIFICATIONID, builder)
                }
                "callback" -> {
                    MyApplication.instance.startActivity(Intent(MyApplication.instance,ReciveBroadcastActivity::class.java))
// This can be written before. Now only one activity can be used to simulate the dialog.
//Because the context received by the intent here is a BroadcastReceiver.The context required by the dialog must be an activity.
//SYSTEM_ALERT_WINDOW calls the system alert. But Google does not allow this now.

//                    val type = AlertDialog.Builder(MyApplication.instance)
//                        .setMessage(intent.getStringExtra("data"))
//                        .setTitle(intent.getStringExtra("title")).create()
//                    type.window?.setType(WindowManager.LayoutParams.TYPE_TOAST)
//                    type.show()
                }
                else -> {
                }
            }

        }

    }

    fun resultIntent(): PendingIntent? {
        val resultIntent = Intent(MyApplication.instance, ReciveBroadcastActivity::class.java)
        // Create the TaskStackBuilder
        val resultPendingIntent: PendingIntent? =
            TaskStackBuilder.create(MyApplication.instance).run {
                // Add the intent, which inflates the back stack
                addNextIntentWithParentStack(resultIntent)
                // Get the PendingIntent containing the entire back stack
                getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
            }
        return resultPendingIntent
    }

    fun snoozeIntent(): Intent {
        val intent = Intent(MyApplication.instance, MyBroadcastReceiver::class.java)
        intent.setAction(NOTIFICATKIONCALLBACK)
        intent.putExtra("type", "callback")
        intent.putExtra("data", "click something")
        intent.putExtra("title", "callback")
        return intent
    }

}