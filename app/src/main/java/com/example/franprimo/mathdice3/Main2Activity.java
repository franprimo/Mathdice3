package com.example.franprimo.mathdice3;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle bundle = getIntent().getExtras();
        int posicion = bundle.getInt("position");

        if(posicion == 0){
            PerfilFragment pf = new PerfilFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.static2fragment, pf);
            transaction.commit();
        }
        if(posicion == 1){
            DetalleFragment df = new DetalleFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.static2fragment, df);
            transaction.commit();
        }
        if(posicion == 2){
            Toast t;
            t = Toast.makeText(this, "Esto abrirá en el fragment estatico las instrucciones", Toast.LENGTH_LONG);
            t.show();
        }
        if(posicion == 3){
            Toast t;
            t = Toast.makeText(this, "Esto abrirá en el fragment estatico la información", Toast.LENGTH_LONG);
            t.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
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
