package com.example.franprimo.mathdice3;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DetalleFragment extends Fragment {

    public DetalleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //declaracionViews();

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

    }


}
