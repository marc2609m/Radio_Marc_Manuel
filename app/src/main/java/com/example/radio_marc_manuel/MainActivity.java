package com.example.radio_marc_manuel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton Button1;
    private FloatingActionButton Button2;
    private FloatingActionButton Button3;
    private FloatingActionButton Button4;
    private HashMap<Integer, String> soundsLinks = new HashMap<>();
    private HashMap<Integer, String> nameLinks = new HashMap<>();
    private HashMap<Integer, String> descriptionLinks = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button1 = findViewById(R.id.Boton1);
        Button2 = findViewById(R.id.Boton2);
        Button3 = findViewById(R.id.Boton3);
        Button4 = findViewById(R.id.Boton4);
        inicialitzarMaps();
        posarImatgesBotons();
    }

    public void clickarBoton(View view){
        Intent intent = new Intent(this, ReproductorClass.class);
        intent.putExtra("audio", soundsLinks.get(view.getId()));
        intent.putExtra("name", nameLinks.get(view.getId()));
        intent.putExtra("desc", descriptionLinks.get(view.getId()));
        this.startActivity(intent);
    }

    private void inicialitzarMaps(){
        soundsLinks.put(R.id.Boton1, "https://shoutcast.ccma.cat/ccma/catalunyaradioHD.mp3");
        nameLinks.put(R.id.Boton1, "Catalunya Ràdio");
        descriptionLinks.put(R.id.Boton1, "Descripcio 1");

        soundsLinks.put(R.id.Boton2, "https://shoutcast.ccma.cat/ccma/catalunyainformacioHD.mp3");
        nameLinks.put(R.id.Boton2, "Catalunya Informació");
        descriptionLinks.put(R.id.Boton2, "Descripcio 2");

        soundsLinks.put(R.id.Boton3, "https://shoutcast.ccma.cat/ccma/catalunyamusicaHD.mp3");
        nameLinks.put(R.id.Boton3, "Catalunya Mùsica");
        descriptionLinks.put(R.id.Boton3, "Descripcio 3");

        soundsLinks.put(R.id.Boton4, "https://shoutcast.ccma.cat/ccma/icatHD.mp3");
        nameLinks.put(R.id.Boton4, "Icat FM ");
        descriptionLinks.put(R.id.Boton4, "Descripcio 4");
    }

    private void posarImatgesBotons(){
        Drawable drawable = getResources().getDrawable(R.drawable.catalunyaradio);
        Button1.setImageDrawable(drawable);
    }
}