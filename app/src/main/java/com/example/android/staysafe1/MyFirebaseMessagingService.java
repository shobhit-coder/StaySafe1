package com.example.android.staysafe1;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static com.example.android.staysafe1.Constants.CHANNEL_ID;

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d("Message Received", "From: " + remoteMessage.getFrom());
        Intent intent=new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("message",remoteMessage.getData().toString());
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0/*request code*/,intent,PendingIntent.FLAG_ONE_SHOT);

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d("datapayload", "Message data payload: " + remoteMessage.getData());
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.unnamed)
                    .setContentTitle("Alerts Received")
                    .setContentText("Tap here to look at the updated map.")
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText("Tap here to look at the updated map."))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

            // notificationId is a unique int for each notification that you must define
            notificationManager.notify(1, mBuilder.build());

//            NotificationCompat.Builder notifiBuilder=new NotificationCompat.Builder(this)
//                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setContentTitle("Alerts Received")
//                    .setContentText("Tap here to look at the updated map.")
//                    .setAutoCancel(true)
//                    .setContentIntent(pendingIntent);
//            NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//            notificationManager.notify(0/*ID of notification*/,notifiBuilder.build());



            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
//                scheduleJob();
            } else {
                // Handle message within 10 seconds
//                handleNow();
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d("codefundonotification", "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
}
