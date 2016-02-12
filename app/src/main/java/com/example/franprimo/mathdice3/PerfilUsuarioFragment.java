package com.example.franprimo.mathdice3;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PerfilUsuarioFragment extends Fragment {

    buttonListener mButton;

    public interface buttonListener{
        public void onClick(String name, String surname, String root);
    }

    MyDBAdapter myDBAdapter;
    Button fotoBtn;
    Button guardarBtn;
    Spinner spinner;
    EditText nombre;
    EditText apellidos;
    //Fichero de guardado
    private Uri fileUri;
    //Tipos definidos
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private String rutaUsuario;
    private static String fichero;

    String nombreUsuario;
    String apellidoUsuario;

    public PerfilUsuarioFragment() {
        // Required empty public constructor
    }

    /** Create a file Uri for saving an image or video */
    private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    public void creacionViews(){
        fotoBtn = (Button) getActivity().findViewById(R.id.fotoBtn);
        guardarBtn = (Button) getActivity().findViewById(R.id.guardarBtn);
        nombre = (EditText) getActivity().findViewById(R.id.editText);
        apellidos = (EditText) getActivity().findViewById(R.id.editText2);
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
            fichero = String.valueOf(mediaFile);
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        creacionViews();


        fotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // create Intent to take a picture and return control to the calling application
                Intent camara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
                camara.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name

                // start the image capture Intent
                startActivityForResult(camara, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                Log.i("Guardado en:", fichero);
            }
        });


        guardarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombreUsuario = nombre.getText().toString();
                apellidoUsuario = apellidos.getText().toString();
                rutaUsuario = fichero;
                mButton.onClick(nombreUsuario, apellidoUsuario, rutaUsuario);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil_usuario, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mButton = (buttonListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ListFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mButton = null;
    }

}
