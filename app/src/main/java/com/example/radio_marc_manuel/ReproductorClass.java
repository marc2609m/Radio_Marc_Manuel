package com.example.radio_marc_manuel;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReproductorClass extends AppCompatActivity {
    private TextView textViewNom;
    private TextView textViewDescription;
    private String audioLink;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_reproductor);
        textViewNom = findViewById(R.id.TextoRadio);
        textViewDescription = findViewById(R.id.DescripcionText);
        Intent intent = this.getIntent();
        textViewNom.setText(intent.getStringExtra("name"));
        textViewDescription.setText(intent.getStringExtra("desc"));
        audioLink = intent.getStringExtra("audio");
        mp = MediaPlayer.create(this, Uri.parse(audioLink));

    }

    public void tornar(View view){
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }

    public void PlaySound(View view){
        if(mp != null){
            this.mp.start();
        }
    }

    public void StopSound(View view){
        if(this.mp != null && this.mp.isPlaying()){
            this.mp.pause();
        }
    }
}
