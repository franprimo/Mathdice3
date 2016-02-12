package com.example.franprimo.mathdice3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by FranPrimo on 12/2/16.
 */
public class SpinnerAdapter extends ArrayAdapter<String> {

    private Context context;
    private ArrayList<String> datos;

    public SpinnerAdapter(Context context, ArrayList<String> opcionesMenu) {
        super(context, 0, opcionesMenu);
        this.context = context;
        this.datos = opcionesMenu;
    }

    public View getView(int position, android.view.View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_spinner, parent, false);
            TextView texto = (TextView) convertView.findViewById(R.id.textView3);

            texto.setText(datos.get(position));

        }
        return convertView;
    }


}
