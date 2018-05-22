package com.alvarado.pe.labcalificado3.models;

/**
 * Created by Alumno on 11/05/2018.
 */

public class Reporte {

    private String titulo;
    private String desc;
    private String ubicacion;
    private String foto;
    private String ciudadano_idciudadano;
    private int id;

    @Override
    public String toString() {
        return "Reporte{" +
                "titulo='" + titulo + '\'' +
                ", desc='" + desc + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", foto='" + foto + '\'' +
                ", ciudadano_idciudadano='" + ciudadano_idciudadano + '\'' +
                ", id=" + id +
                '}';
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCiudadano_idciudadano() {
        return ciudadano_idciudadano;
    }

    public void setCiudadano_idciudadano(String ciudadano_idciudadano) {
        this.ciudadano_idciudadano = ciudadano_idciudadano;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Reporte() {

    }

    public Reporte(String titulo, String desc, String ubicacion, String foto, String ciudadano_idciudadano, int id) {
        this.titulo = titulo;
        this.desc = desc;
        this.ubicacion = ubicacion;
        this.foto = foto;
        this.ciudadano_idciudadano = ciudadano_idciudadano;
        this.id = id;
    }
}