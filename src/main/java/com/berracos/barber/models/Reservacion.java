package com.berracos.barber.models;

import com.berracos.barber.enums.EstadoReservacion;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.OffsetDateTime;

@Entity
@Table(name = "\"Reservacion\"")
public class Reservacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "\"nombreCliente\"", nullable = false, columnDefinition = "text")
    private String nombreCliente;

    @Column(name = "\"telefono\"", nullable = false, columnDefinition = "text")
    private String telefono;

    @Column(name = "\"fecha\"", nullable = false)
    private OffsetDateTime fecha;

    @Column(name = "\"hora\"", nullable = false, columnDefinition = "text")
    private String hora;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoReservacion estado = EstadoReservacion.PENDIENTE;

    @Column(name = "\"createdAt\"", nullable = false, insertable = false, updatable = false)
    private OffsetDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "\"barberoId\"", nullable = false)
    private Barbero barbero;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "\"servicioId\"", nullable = false)
    private Servicio servicio;

    public Reservacion() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public OffsetDateTime getFecha() {
        return fecha;
    }

    public void setFecha(OffsetDateTime fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public EstadoReservacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoReservacion estado) {
        this.estado = estado;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public Barbero getBarbero() {
        return barbero;
    }

    public void setBarbero(Barbero barbero) {
        this.barbero = barbero;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
}

