package com.addusername.pmdm7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * from https://openwebinars.net/blog/como-hacer-notificaciones-push-en-android-facil/ updated for
 * API >= 26, (see lines 32-35) by https://stackoverflow.com/a/44524976/13771772
 */
public class MainActivity extends AppCompatActivity {

        Button mostrarNotificacion;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mostrarNotificacion=(Button)findViewById(R.id.button);
            mostrarNotificacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NotificationCompat.Builder mBuilder;
                    NotificationManager mNotifyMgr =(NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

                    // Auto generated if
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        // We need to pass an channel to mNotifyMgr if SDK >=26
                        NotificationChannel channel = new NotificationChannel("default","chanel name",NotificationManager.IMPORTANCE_DEFAULT);
                        mNotifyMgr.createNotificationChannel(channel);
                    }

                    int icono = R.drawable.ic_noti;
                    Intent i=new Intent(MainActivity.this, Noti.class);
                    // This activity will be showed when users click in notification push.
                    PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, i, 0);

                    // Before api 26, there was no need to pass channelId
                    mBuilder =new NotificationCompat.Builder(getApplicationContext(),"default")
                            .setContentIntent(pendingIntent)
                            .setSmallIcon(icono)
                            .setContentTitle("Titulo")
                            .setContentText("Hola que tal?")
                            .setVibrate(new long[] {100, 250, 100, 500})
                            .setAutoCancel(true);

                    mNotifyMgr.notify(1, mBuilder.build());
                }
            });
        }
    }
