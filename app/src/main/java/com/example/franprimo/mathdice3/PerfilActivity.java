package com.example.franprimo.mathdice3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PerfilActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        //Cargo el fragment en su contenedor estatico
        PerfilFragment pf = (PerfilFragment) getFragmentManager().findFragmentById(R.id.containerPerfil);


        final EditText nombre = (EditText) findViewById(R.id.inputName);
        final EditText anyos = (EditText) findViewById(R.id.inputEdad);


        //Listener del boton que nos pasara al MainActivity.
        Button btn = (Button) findViewById(R.id.adelanteBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Recojo los datos introducidos en el editText y los almaceno en variables
                //que voy a enviar junto con el intent.
                String name = nombre.getText().toString();
                String edad = anyos.getText().toString();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("nombre", name);
                intent.putExtra("edad", edad);
                startActivity(intent);

            }
        });

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
