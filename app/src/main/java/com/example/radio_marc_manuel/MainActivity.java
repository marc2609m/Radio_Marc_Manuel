package com.example.radio_marc_manuel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton Button1;
    private FloatingActionButton Button2;
    private FloatingActionButton Button3;
    private HashMap<Integer, String> nameLinks = new HashMap<>();
    private HashMap<Integer, String> descriptionLinks = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button1 = findViewById(R.id.Boton1);
        Button2 = findViewById(R.id.Boton2);
        Button3 = findViewById(R.id.Boton3);
        inicialitzarMaps();
        solictarPermisoNotficaciones();
    }

    public void clickarBoton(View view){
        Intent intent = new Intent(this, ReproductorClass.class);
        intent.putExtra("name", nameLinks.get(view.getId()));
        intent.putExtra("desc", descriptionLinks.get(view.getId()));
        this.startActivity(intent);
    }

    private void inicialitzarMaps(){
        nameLinks.put(R.id.Boton1, "Los 40");
        descriptionLinks.put(R.id.Boton1, "Los 40 es una cadena de radio musical y marca de emisoras de radio Top 40 en muchos países de habla hispana");

        nameLinks.put(R.id.Boton2, "Los 40 classic");
        descriptionLinks.put(R.id.Boton2, "Los 40 Classic es una cadena de radio española de temática musical, perteneciente a Prisa Radio que en 2018 sustituyó a M80 Radio");

        nameLinks.put(R.id.Boton3, "Los 40 dance");
        descriptionLinks.put(R.id.Boton3, "Los 40 Dance es una cadena de radio española de temática musical, perteneciente a Prisa Radio que en 2019 sustituyó a Máxima FM.");
    }

    private void solictarPermisoNotficaciones(){
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.POST_NOTIFICATIONS)
                == PackageManager.PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 100);
        }
    }
}