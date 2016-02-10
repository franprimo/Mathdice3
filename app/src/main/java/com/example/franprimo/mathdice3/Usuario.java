package com.example.franprimo.mathdice3;

/**
 * Created by FranPrimo on 9/2/16.
 */
public class Usuario {

    private String nombre;
    private String apellidos;
    private String rutaFoto;

    public Usuario(){

    }

    public Usuario(String nombre, String apellidos, String root){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.rutaFoto = root;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }
}
