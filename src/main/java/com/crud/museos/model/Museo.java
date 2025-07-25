package com.crud.museos.model;

import java.util.List;

public class Museo {
    private String id;
    private String nombre;
    private String direccion;
    private String horarios;
    private String descripcion;
    private List<String> fotos; // URLs o nombres de las imágenes

    // Constructor vacío requerido por Firebase
    public Museo() {}

    // Constructor con parámetros (útil si lo necesitas)
    public Museo(String id, String nombre, String direccion, String horarios, String descripcion, List<String> fotos) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.horarios = horarios;
        this.descripcion = descripcion;
        this.fotos = fotos;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }

    @Override
    public String toString() {
        return "Museo{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", horarios='" + horarios + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fotos=" + fotos +
                '}';
    }
}
