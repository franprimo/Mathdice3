package com.example.franprimo.mathdice3;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class PerfilFragment extends Fragment {

    buscarBtnListener mBuscar;
    registroListener mRegistro;

    private String nombreUser;
    private Spinner spinner;
    private String rutaFoto;
    private boolean existeUsuario = false;
    private SpinnerAdapter adapter;
    private MyDBAdapter myDBAdapter;
    private ArrayList<String> usuarios;

    public interface buscarBtnListener{
        public void onClick(int idUser);
    }

    public interface registroListener{
        public void irARegistro();
    }

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Declaraciones.
        TextView label = (TextView) getActivity().findViewById(R.id.nombreTextV);
        spinner = (Spinner) getActivity().findViewById(R.id.spinner);
        Button buscarBtn = (Button) getActivity().findViewById(R.id.adelanteBtn);
        ImageView fotoPerfil = (ImageView) getActivity().findViewById(R.id.imageView4);

        rellenaSpinner();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mBuscar.onClick((int) id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buscarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRegistro.irARegistro();
            }
        });
    }

    public void rellenaSpinner(){
        myDBAdapter = new MyDBAdapter(getActivity());
        myDBAdapter.open();

        usuarios = myDBAdapter.recuperarUsuarios();
        adapter = new SpinnerAdapter(getActivity(), usuarios);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    /*
    //Listener propio para la lista.
    private abstract class nuestroListener implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            mBuscar.onClick((int) id);
        }
    }
    */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    public void recogeUsuario(ArrayList<String> usuariosBBDD){
        usuarios = usuariosBBDD;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mBuscar = (buscarBtnListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ListFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mBuscar = null;
    }


}
