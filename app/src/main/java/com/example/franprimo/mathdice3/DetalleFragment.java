package com.example.franprimo.mathdice3;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class DetalleFragment extends Fragment {

    private AudioManager audioManager;
    private MediaPlayer mPlayer;
    private int sonando = 0;
    private boolean puedeReproducir;

    public DetalleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Arrancamos el servicio de audio.
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);


        //declaracionViews();

        controlaAudio();

    }
    /*
    private void declaracionViews() {
        final ImageView rojo1 = (ImageView)getView().findViewById(R.drawable.dado1);
        final ImageView rojo2 = (ImageView)getView().findViewById(R.drawable.dado2);
        final ImageView rojo3 = (ImageView)getView().findViewById(R.drawable.dado3);
        final ImageView azul1 = (ImageView)getView().findViewById(R.drawable.dado1_6);
        final ImageView azul2 = (ImageView)getView().findViewById(R.drawable.dado2_6);
        final ImageView azul3 = (ImageView)getView().findViewById(R.drawable.dado3_6);

    }
    */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detalle, container, false);

        return v;
    }

    //metodo con el que controlo el audio del fragment del juego.
    public void controlaAudio(){
        //Cargamos la cancion
        mPlayer = MediaPlayer.create(getActivity(), R.raw.sue);
        //Como no he puesto botones para controlar el audio, solo tengo que poner en marcha el
        //audio.
        mPlayer.start();
        /*
        if(sonando == 0){
            sonando = 1;

            mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    Log.d("AUDIO: ", "Cargada la cancion");
                    bPlay.setEnabled(true);
                }
            });
            mPlayer.start();
        }
        if(sonando == 1){
            sonando = 2;
            mPlayer.pause();
        }else{
            sonando = 1;
            mPlayer.start();
        }
        */
    }

    // Get ready to play sound effects
    @Override
    public void onResume() {
        Log.d("AUDIO", "VOLVIENDO A TOCAR");
        super.onResume();
        audioManager.setSpeakerphoneOn(true);
        audioManager.loadSoundEffects();
    }

    // Release resources & clean up
    @Override
    public void onPause() {
        Log.d("AUDIO", "EN PAUSA");
        audioManager.setSpeakerphoneOn(false);
        audioManager.unloadSoundEffects();
        super.onPause();
    }

    // Listen for Audio focus changes
    AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                audioManager.abandonAudioFocus(afChangeListener);
                puedeReproducir = false;
            }
        }
    };

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        //Tengo que poner esta linea de codigo para que si cambio el fragment dinamico la musica se pare
        //y que si vuelvo a abrir el fragment del juego no arranque otra cancion encima de la que ya esta
        //sonando.
        mPlayer.release();
    }


}
