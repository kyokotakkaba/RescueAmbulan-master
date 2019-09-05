package com.example.abyandafa.rescueambulance.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.abyandafa.rescueambulance.CodeBlue.Locate.CodeBlue;
import com.example.abyandafa.rescueambulance.CodeBlue.Locate.MenujuLokasi;
import com.example.abyandafa.rescueambulance.R;
import com.example.abyandafa.rescueambulance.model.SPTransaksi;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Calendar;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by Abyan Dafa on 06/12/2017.
 */

public class MessagingService extends FirebaseMessagingService {

    SPTransaksi spTransaksi;
    RemoteViews notificationView;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        super.onMessageReceived(remoteMessage);
        spTransaksi = new SPTransaksi(this);
        Map<String, String> data = remoteMessage.getData();
        Log.d(TAG, "onMessageReceived: "+ data.get("tipe"));
        spTransaksi.saveSPString(SPTransaksi.SP_TIPE, data.get("tipe"));
        spTransaksi.saveSPString(SPTransaksi.SP_ID, data.get("id_transaksi"));
        spTransaksi.saveSPString(SPTransaksi.SP_LATITUDE, data.get("latitude"));
        spTransaksi.saveSPString(SPTransaksi.SP_LONGITUDE, data.get("longitude"));
        spTransaksi.saveSPString(SPTransaksi.SP_LOKASI, data.get("lokasi"));

        Log.d(TAG, "onMessageReceived: id transaksi:"+ data.get("id_transaksi")+ " | lokasi: "+data.get("lokasi")+ " butuh pertolongan");

        String path = "default";
        Log.d("belummasukifbyan", "onMessageReceived: " + data.get("foto"));
        if(data.get("foto")!=null)
        {
            Log.d("masukifbyan", "onMessageReceived: " + data.get("foto"));
            path = data.get("foto");
        }


        Log.d("setelahifbyan", "onMessageReceived: " + path);


        //Notification di bawah tidak muncul
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher);
        Intent intent = new Intent(this, CodeBlue.class);



        //setNotification(spTransaksi.getSpTipe());
        if(spTransaksi.getSpTipe().equals("1"))
        {
            mBuilder.setContentTitle("Panic Button");
            intent = new Intent(this, com.example.abyandafa.rescueambulance.Notification.Notification.class);
        }
        else if(spTransaksi.getSpTipe().equals("2"))
        {
            mBuilder.setContentTitle("Code Blue");
            intent = new Intent(this, com.example.abyandafa.rescueambulance.Notification.Notification.class);
        }

        intent.putExtra("path", path);


        Log.d(TAG, "onMessageReceived: ");

       mBuilder.setContentText("Ada yang butuh pertolonganmu")
               .setPriority(Notification.PRIORITY_HIGH)
               .setAutoCancel(true);


        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setFullScreenIntent(pendingIntent, true);
        mBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(123);
        notificationManager.notify(123,mBuilder.build());



    }

    private void setNotification(String spTipe) {
        notificationView = new RemoteViews(
                getApplication().getPackageName(),
                R.layout.notification_layout
        );
        Intent doingActIntent = new Intent(getApplicationContext(), MenujuLokasi.class);
        // Intent addActIntent = new Intent(getContext(),AddActivity.class);

        PendingIntent doingPendingIntent = PendingIntent.getActivity(getApplicationContext(),0,doingActIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        notificationView.setOnClickPendingIntent(R.id.yes_notif,doingPendingIntent);

//        PendingIntent addPendingIntent = PendingIntent.getActivity(getContext(),0,addActIntent,PendingIntent.FLAG_UPDATE_CURRENT);
//        notificationView.setOnClickPendingIntent(R.id.imageView3,addPendingIntent);


        Calendar calendar = Calendar.getInstance();
        Log.d(TAG, "showNotification: " + spTransaksi.getSpLokasi());
        notificationView.setTextViewText(R.id.lokasikorban, spTransaksi.getSpLokasi());

        android.support.v7.app.NotificationCompat.Builder builder = (android.support.v7.app.NotificationCompat.Builder) new android.support.v7.app.NotificationCompat.Builder(getApplicationContext())
                // Set Icon
                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("Waktu Kosong")
//                .setContentText("geser ke bawah untuk melihat pilihan")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.dekat_logo))
                // Set Ticker Message
                .setTicker("ticker")
                // Dismiss Notification
                .setAutoCancel(true)
                // Set RemoteViews into Notification
                .setContent(notificationView)
                .setCustomBigContentView(notificationView)
                .setPriority(Notification.PRIORITY_HIGH)
                .setDefaults(Notification.DEFAULT_ALL)
                .setStyle(new android.support.v4.app.NotificationCompat.BigTextStyle().bigText("big text"))
                .setCustomHeadsUpContentView(notificationView);
        Notification notification = builder.build();
        //notification.bigContentView = notificationView;

//        android.support.v4.app.NotificationCompat.BigTextStyle bigTextStyle = new android.support.v4.app.NotificationCompat.BigTextStyle();
//        bigTextStyle.

        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,notification);
    }
}
