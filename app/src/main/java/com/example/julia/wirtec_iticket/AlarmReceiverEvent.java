package com.example.julia.wirtec_iticket;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Glenn on 4/10/2017.
 */

public class AlarmReceiverEvent extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.logo_no_background);

        Intent intentMA = new Intent();
        intentMA.setClass(context, NavDrawer.class);

        PendingIntent pendingMA = PendingIntent.getActivity(
                context,
                AddEvent.PENDING_MA,
                intentMA,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.logo_no_background)
                .setTicker("Event")
                .setContentTitle("Event")
                .setContentText("You have an event within 3 days.")
                .setLargeIcon(icon)
                .setContentIntent(pendingMA)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Service.NOTIFICATION_SERVICE);
        notificationManager.notify(AddEvent.NOTIFICATION_ID_MATCH, notifBuilder.build());
    }
}
