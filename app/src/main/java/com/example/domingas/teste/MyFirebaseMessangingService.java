package com.example.domingas.teste;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessangingService extends FirebaseMessagingService {

    private static final String TAG = "fcmexempleMessage" ;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
    Log.d(TAG, "From: "+ remoteMessage.getFrom());
    Log.d(TAG, "Notification Message Body: "+ remoteMessage.getNotification().getBody());
        notifyUser(remoteMessage.getFrom(), remoteMessage.getNotification().getBody());

    }
    public void notifyUser(String from, String notification){
        MyNotificationManager myNotificationManager = new MyNotificationManager(getApplicationContext());
        Intent intent = new Intent(getApplicationContext(), Principal.class);
        intent.putExtra("notify", notification);
        myNotificationManager.showNotification(from, notification, intent);

    }
}
