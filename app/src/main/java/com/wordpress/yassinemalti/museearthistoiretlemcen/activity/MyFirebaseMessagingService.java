package com.wordpress.yassinemalti.museearthistoiretlemcen.activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.wordpress.yassinemalti.museearthistoiretlemcen.R;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by yassinemalti on 17/03/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String shortMessageBody = remoteMessage.getData().get("shortMessage");
        String longMessageBody = remoteMessage.getData().get("longMessage");
        String imageUri = remoteMessage.getData().get("imageUri");
        sendNotification(shortMessageBody, longMessageBody, imageUri);

    }

    private void sendNotification(String shortMessageBody, String longMessageBody, String imageUri) {

        Intent intent = new Intent(this, PrincipaleActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("imageUri", imageUri);
        intent.putExtra("shortMessageBody", shortMessageBody);
        intent.putExtra("longMessageBody", longMessageBody);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String Title = getString(R.string.app_name);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.column)
                .setContentTitle(Title)
                .setContentText(shortMessageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());

    }
}
