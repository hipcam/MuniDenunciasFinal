package com.alvarado.pe.labcalificado3.models;

/**
 * Created by alexi on 14/05/2018.
 */

public class Ciudadano {
    private String password;
    private String usuario;
    private String correo;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Ciudadano{" +
                "password='" + password + '\'' +
                ", usuario='" + usuario + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }
}
