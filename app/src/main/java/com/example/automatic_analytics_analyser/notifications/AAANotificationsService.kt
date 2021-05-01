package com.example.automatic_analytics_analyser.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.automatic_analytics_analyser.R
import com.example.automatic_analytics_analyser.data.repositories.ChatRepository
import com.example.automatic_analytics_analyser.data.repositories.RepositoryProvider
import com.example.automatic_analytics_analyser.data.repositories.UserManagmentRepository
import com.example.automatic_analytics_analyser.model.FCMToken
import com.example.automatic_analytics_analyser.model.NewMessage
import com.example.automatic_analytics_analyser.model.User
import com.example.automatic_analytics_analyser.view.MainActivity
import com.example.automatic_analytics_analyser.view.fragments.DrawerActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.android.AndroidInjection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class AAANotificationsService : FirebaseMessagingService() {
    @Inject
    lateinit var userManagmentRepository: UserManagmentRepository

    @Inject
    lateinit var chatRepository: ChatRepository

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    override fun onNewToken(token: String) {
        CoroutineScope(Dispatchers.IO + Job()).launch {
            userManagmentRepository.insertFCMToken(FCMToken(token))
        }

    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.data["category"].equals(NotificationType.CHAT.name)){
            var message = NewMessage(remoteMessage.data["content"]!!, remoteMessage.data["user"]!!, remoteMessage.data["chatId"]!!)
            if(!chatRepository.handleMessage(message)){
                sendNotification(message.content, message.user, NotificationType.CHAT.name)
            }

        } else {
            sendNotification(
                remoteMessage.data["body"],
                remoteMessage.data["title"],
                remoteMessage.data["category"]
            )
        }
    }

    private fun sendNotification(messageBody: String?, title: String?, category: String?) {
        val type = NotificationType.fromString(category!!)
        val intent = Intent(this, DrawerActivity::class.java)

        intent.putExtra("fragmentId", type.id)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0 /* Request code */, intent,
            PendingIntent.FLAG_ONE_SHOT
        )

        val channelId = "AAAChannel"
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_prescripcion)
            .setContentTitle(title)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(
                    channelId,
                    "AAAChannel",
                    NotificationManager.IMPORTANCE_DEFAULT
                )

            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }

    override fun onDeletedMessages() {

    }

}