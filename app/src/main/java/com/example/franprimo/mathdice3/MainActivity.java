package com.example.franprimo.mathdice3;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity implements ListaFragment.ListFragmentListener, PerfilUsuarioFragment.buttonListener {

    private MyDBAdapter myDBAdapter;
    String name, surname, root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {
            //Recojo los datos que he enviado con el intent que pasaba a esta activity y se los asigno
            //a dos variables.
            Bundle bundle = getIntent().getExtras();
            String nombre = bundle.getString("nombre");
            String edad = bundle.getString("edad");

            //Muestro en consola los datos recibidos con el intent
            Log.i("Nombre:", nombre);
            Log.i("Edad:", edad);

            //Coloco el fragment lista en su contenedor estatico
            ListaFragment lf = (ListaFragment) getFragmentManager().findFragmentById(R.id.listaFragment);

            //Añado el vacio fragment al container
            VacioFragment vf = new VacioFragment();
            getFragmentManager().beginTransaction().add(R.id.fragment_container, vf).commit();
        }else{
            ListaFragment lf = (ListaFragment) getFragmentManager().findFragmentById(R.id.listaFragment);
        }
    }

    //Tenemos que crear este metodo porque es el que implementa al ListFragmentListener
    public void onListSelected(int position){
        if(position == 0){
            if (findViewById(R.id.fragment_container) != null) {
                PerfilUsuarioFragment pUf = new PerfilUsuarioFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, pUf);
                transaction.commit();
            }else{
                Intent intent = new Intent(this, Main2Activity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        }

        if(position == 1){
            if (findViewById(R.id.fragment_container) != null) {
                DetalleFragment df = new DetalleFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, df);
                transaction.commit();
            }else{
                Intent intent = new Intent(this, Main2Activity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        }

        if(position == 2){
            Toast t;
            t = Toast.makeText(this, "Esto abrirá el fragment dinamico de instrucciones", Toast.LENGTH_LONG);
            t.show();
        }

        if(position == 3){
            Toast t;
            t = Toast.makeText(this, "Esto abrirá el fragment dinamico de información", Toast.LENGTH_LONG);
            t.show();
        }
    }

    //Metodo que implementa el listener del boton guardar del fragment perfil usuario.
    public void onClick(String name, String surname, String root){
        this.name = name;
        this.surname = surname;
        this.root = root;
        myDBAdapter.insertarUsuario(name, surname, root);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
