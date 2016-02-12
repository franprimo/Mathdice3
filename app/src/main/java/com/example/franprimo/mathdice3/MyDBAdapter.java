package com.example.franprimo.mathdice3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by FranPrimo on 9/2/16.
 */
public class MyDBAdapter {

    //Definición de la BBDD.
    private static final String DATABASE_NAME = "mathdice.db";
    private static final String DATABASE_TABLE = "usuarios";
    private static final int DATABASE_VERSION = 1;

    //Definición campos de la tabla usuarios.
    private static final String NOMBRE = "nombre";
    private static final String APELLIDOS = "apellidos";
    private static final String RUTAFOTO = "ruta";

    //Definición de creacion y borrado de la tabla usuarios.
    private static final String DATABASE_CREATE =
            "CREATE TABLE " + DATABASE_TABLE +
                    " (_id integer primary key autoincrement, nombre text, apellidos text, ruta text);";
    private static final String DATABASE_DROP = "DROP TABLE IF EXISTS " + DATABASE_TABLE + ";";

    // Contexto de la aplicación que usa la base de datos
    private final Context context;
    // Clase SQLiteOpenHelper para crear/actualizar la base de datos
    private MyDbHelper dbHelper;
    // Instancia de la base de datos
    private SQLiteDatabase db;

    public MyDBAdapter(Context c) {
        context = c;
        dbHelper = new MyDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        //OJO open();
    }

    public void open() {
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException e) {
            db = dbHelper.getReadableDatabase();
        }
    }

    //Metodo al que llamaremos para realizar los inserts de los usuarios en la BBDD.
    public void insertarUsuario(String nombre, String apellidos, String ruta){
        //Creamos un nuevo registro de valores a insertar
        ContentValues cValues = new ContentValues();
        //Asignamos los valores de cada campo
        cValues.put(NOMBRE, nombre);
        cValues.put(APELLIDOS, apellidos);
        cValues.put(RUTAFOTO, ruta);
        //Realizamos el insert en la BBDD.
        db.insert(DATABASE_TABLE, null, cValues);
    }

    //Metodo al que llamaremos para recuperar los usuarios de la BBDD.
    public ArrayList<String> recuperarUsuarios(){
        ArrayList<String> usuarios = new ArrayList<>();
        Cursor cursor = db.query(DATABASE_TABLE, null, null, null, null, null, null);
        if(cursor != null && cursor.moveToFirst()){
            do{
                //Recojo todos los datos del usuario(ID, nombre, apellido y ruta).
                usuarios.add(cursor.getString(0)+" "+cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3));
            }while(cursor.moveToNext());
        }
        return usuarios;
    }

    public ArrayList<Usuario> recuperarUsuarioPorNombre(String name){
        ArrayList<Usuario> usuario = new ArrayList<>();
        Cursor cursor = db.query(DATABASE_TABLE, null, "nombre ='"+name+"'", null, null, null, null);
        if(cursor != null && cursor.moveToFirst()){
            do{
                //usuario.add(cursor.getString(0)+" "+cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3));
                Usuario u = new Usuario();
                u.setNombre(cursor.getString(1));
                u.setRutaFoto(cursor.getString(3));
                usuario.add(u);
            }while (cursor.moveToNext());
        }
        return usuario;
    }

    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_DROP);
            onCreate(db);
        }

    }

}
