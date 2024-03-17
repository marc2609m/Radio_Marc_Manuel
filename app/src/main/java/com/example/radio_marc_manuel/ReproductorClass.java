package com.example.radio_marc_manuel;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.io.IOException;

public class ReproductorClass extends AppCompatActivity {
    private TextView textViewNom;
    private TextView textViewDescription;
    private MediaPlayer mp;
    private final String LOS_40 = "https://27833.live.streamtheworld.com/LOS40_SC";
    private final String LOS_40_DANCE = "https://playerservices.streamtheworld.com/api/livestream-redirect/LOS40_DANCE_SC";
    private final String LOS_40_CLASSIC = "https://playerservices.streamtheworld.com/api/livestream-redirect/LOS40_CLASSIC_SC";
    private static String nombre;
    private static String description;
    private Uri audioLink;
    private NotificationCompat.Builder builder;
    private NotificationManagerCompat nmc;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_reproductor);
        textViewNom = findViewById(R.id.TextoRadio);
        textViewDescription = findViewById(R.id.DescripcionText);
        createNotificationChannel();
        Intent intent = this.getIntent();
        nombre = intent.getStringExtra("name");
        description = intent.getStringExtra("desc");
        textViewNom.setText(nombre);
        textViewDescription.setText(description);
        if (textViewNom.getText().equals("Los 40")) {
            audioLink = Uri.parse(LOS_40);
        } else if (textViewNom.getText().equals("Los 40 classic")) {
            audioLink = Uri.parse(LOS_40_CLASSIC);
        } else if (textViewNom.getText().equals("Los 40 dance")) {
            audioLink = Uri.parse(LOS_40_DANCE);
        }

        context = this.getApplicationContext();

        new Handler().postDelayed(new Runnable() {
            public void run() {
                mp = MediaPlayer.create(context, audioLink);
            }
        }, 1000);



        int idImagen = 0;

        if (textViewNom.getText().equals("Los 40")) {
            idImagen = R.drawable.los40;
        } else if (textViewNom.getText().equals("Los 40 classic")) {
            idImagen = R.drawable.los40classic;
        } else if (textViewNom.getText().equals("Los 40 dance")) {
            idImagen = R.drawable.los40dance;
        }

        Intent intent1 = new Intent(this, ReproductorClass.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent1.putExtra("name", nombre);
        intent1.putExtra("desc", description);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_IMMUTABLE);

        builder = new NotificationCompat.Builder(this, "Canal")
                .setSmallIcon(R.drawable.baseline_radio_24)
                .setContentTitle("Radio Sonando")
                .setContentText("La radio " + textViewNom.getText() + " esta sonando ahora mismo")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent);

        nmc = NotificationManagerCompat.from(this);
    }

    public void tornar(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }

    public void PlaySound(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        nmc.notify(1, builder.build());
        if (mp != null) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    mp.start();
                }
            }, 1500);

        }

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Canal", "MiCanal", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void StopSound(View view){
        nmc.cancel(1);
        if(this.mp != null && this.mp.isPlaying()){
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    mp.pause();
                }
            }, 1500);
        }
    }
}
