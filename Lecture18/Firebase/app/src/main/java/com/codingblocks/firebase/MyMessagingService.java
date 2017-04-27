package com.codingblocks.firebase;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.drawable.Icon;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by championswimmer on 27/04/17.
 */

public class MyMessagingService extends FirebaseMessagingService {

    public static final String TAG = "FM";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "onMessageReceived: messageId" + remoteMessage.getMessageId());
        Log.d(TAG, "onMessageReceived: data" + remoteMessage.getData());
        Log.d(TAG, "onMessageReceived: notification" + remoteMessage.getNotification());

        Notification.Builder notifBldr = new Notification.Builder(this);
        notifBldr.setContentTitle(remoteMessage.getNotification().getTitle());
        notifBldr.setContentText(remoteMessage.getNotification().getBody());
        notifBldr.setSmallIcon(R.mipmap.ic_launcher);

        NotificationManager notMan = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notMan.notify(111, notifBldr.build());


    }
}
