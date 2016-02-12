package com.example.franprimo.mathdice3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class PerfilActivity extends Activity implements PerfilFragment.buscarBtnListener, PerfilFragment.registroListener {

    MyDBAdapter myDBAdapter;
    private String nombreUsuarioABuscar;
    private ArrayList<String> usuarios;
    private String nombre, ruta;
    PerfilFragment pf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        //Cargo el fragment en su contenedor estatico
        pf = (PerfilFragment) getFragmentManager().findFragmentById(R.id.containerPerfil);

        if(checkUsuario(usuarios)){
            pf.recogeUsuario(usuarios);
        }

    }

    //Metodo que implementa el callback del perfilFragment al activity que lo contiene.
    public void onClick(int n){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void irARegistro(){


    }

    public boolean checkUsuario(ArrayList<String> usuario){
        if(usuario != null){
            for(int i=0; i<usuario.size(); i++){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_perfil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
