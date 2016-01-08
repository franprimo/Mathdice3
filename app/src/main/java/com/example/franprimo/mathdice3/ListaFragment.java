package com.example.franprimo.mathdice3;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class ListaFragment extends Fragment {

    ListFragmentListener mCallback;

    public interface ListFragmentListener {
        public void onListSelected(int position);

    }

    public ListaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        //Creo un array con las opciones que tendra el menu
        String[] opcionesMenu = {"PERFIL", "JUEGO", "INSTRUCCIONES", "INFO"};
        Integer[] imagenesMenu = {R.mipmap.perfil, R.mipmap.juego, R.mipmap.instrucciones, R.mipmap.info};

        //Creo un arrayList para obtener una lista
        ArrayList<String> listaMenu = new ArrayList<String>(Arrays.asList(opcionesMenu));
        ArrayList<Integer> listaImagenes = new ArrayList<Integer>(Arrays.asList(imagenesMenu));
        //Creo un adapter y le paso la lista de opciones
        //ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listaMenu);

        //En vez de usar el adaptador normal, uso el propio, y le paso la lista de opciones y las imagenes.
        //En este caso, como no es un activity, en el context no le puedo pasar this, por eso para poder
        //cogerlo uso el getActivity()
        MenuAdapter adapter = new MenuAdapter(getActivity(), listaMenu, listaImagenes);
        //Por ultimo se lo pasamos a la vista que es el listView (VISTA o INTERFAZ):
        //Como aqui no estamos en un activity, sino que es un fragment, tenemos que pasarse el activity
        //desde este, y eso lo hacemos con getActivity().findViewById()
        final ListView lv = (ListView) getActivity().findViewById(R.id.listview);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new nuestroListener());
    }

    //Listener propio para la lista.
    private class nuestroListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {



            mCallback.onListSelected(position);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista, container, false);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (ListFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ListFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }



}
