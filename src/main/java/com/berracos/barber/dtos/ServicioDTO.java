package com.berracos.barber.dtos;

public class ServicioDTO {

    private Integer id;
    private String nombre;
    private Double precio;
    private Integer duracion;
    private Boolean activo;
    private String categoria;

    public ServicioDTO() {
    }

    public ServicioDTO(
            Integer id,
            String nombre,
            Double precio,
            Integer duracion,
            Boolean activo,
            String categoria
    ) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.duracion = duracion;
        this.activo = activo;
        this.categoria = categoria;
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
