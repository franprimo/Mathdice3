package com.example.franprimo.mathdice3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashImage extends Activity {


    //Declaro una constante para el tiempo de retraso en milisegundos.
    private long TIEMPO_RETRASO = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_image);

        //Creo un objeto de tipo TimerTask con el que una vez pasado el tiempo, cambio de activity.
        TimerTask task = new TimerTask(){
            public void run(){
                Intent mainIntent = new Intent(getApplicationContext(), PerfilActivity.class);
                //Cambio del activity imagenPrincipal al activity MainActivity.
                startActivity(mainIntent);

            }
        };

        //Creo un objeto de tipo Timer para poder hacer uso de un contador.
        Timer timer = new Timer();
        timer.schedule(task, TIEMPO_RETRASO);
    }
}
