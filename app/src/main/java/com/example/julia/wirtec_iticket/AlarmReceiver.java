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
 * Created by Glenn on 4/9/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.logo_no_background);

        Intent i = new Intent();
        i.setClass(context, LoginActivity.class);

        PendingIntent p = PendingIntent.getActivity(
                context,
                NavDrawer.PENDING_MA,
                i,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.logo_no_background)
                .setTicker("We found love!")
                .setContentTitle("Event")
                .setContentText("Your event is coming up in 3 days.")
                .setLargeIcon(icon)
                .setContentIntent(p)
                .setAutoCancel(true);

        /*NotificationCompat.Builder notifBuilder2 = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.logo_no_background)
                .setTicker("We found love!")
                .setContentTitle("Ticket")
                .setContentText("You have an event to attend to in 3 days.")
                .setLargeIcon(icon)
                .setContentIntent(p)
                .setAutoCancel(true);*/

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Service.NOTIFICATION_SERVICE);
        notificationManager.notify(NavDrawer.NOTIFICATION_ID_MATCH, notifBuilder.build());
    }
}
