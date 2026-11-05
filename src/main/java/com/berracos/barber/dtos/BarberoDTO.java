package com.berracos.barber.dtos;

public class BarberoDTO {

    private Integer id;
    private String nombre;
    private Boolean activo;

    public BarberoDTO() {
    }

    public BarberoDTO(Integer id, String nombre, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}