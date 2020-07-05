package com.vvitmdc.chats;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String title=remoteMessage.getNotification().getTitle();
        String msg=remoteMessage.getNotification().getBody();
        Map<String,String> extra=remoteMessage.getData();
        UserData.chatWith=extra.get("sender");
        UserData.username=extra.get("receiver");

        NotificationCompat.Builder nb=new NotificationCompat.Builder(this,"MESSAGE")
                .setContentTitle(title)
                .setContentText(msg)
                .setSmallIcon(R.drawable.ic_launcher_background);
        Intent intent;
        intent=new Intent(this,ChatActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,10,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        nb.setContentIntent(pendingIntent);
        NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        int id=(int)System.currentTimeMillis();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel channel=new NotificationChannel("MESSAGE","DEMO",NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(id,nb.build());
    }
}
