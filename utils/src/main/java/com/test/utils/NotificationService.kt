package com.test.utils

import android.app.NotificationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.test.utils.Ext.sendNotification

//class NotificationService : FirebaseMessagingService() {
//
//    override fun onNewToken(token: String) {
//        //.saveNotificationToken(this, token)
//        Log.i(javaClass.simpleName, "Message Notification Body: ${token}")
//
//        super.onNewToken(token)
//    }
//
//
//    override fun onMessageReceived(remoteMessage: RemoteMessage) {
//        remoteMessage.notification?.let {
//            Log.i(javaClass.simpleName, "Message Notification Body: ${it.body}")
//            Log.i(javaClass.simpleName, "Message Notification title: ${it.title}")
//            val notificationManager = ContextCompat.getSystemService(this, NotificationManager::class.java) as NotificationManager
//            notificationManager.sendNotification(it.body.toString(), it.title.toString(), this)
//        }
//    }
//}