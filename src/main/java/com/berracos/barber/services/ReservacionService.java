package com.berracos.barber.services;

import com.berracos.barber.dtos.ReservacionDTO;
import com.berracos.barber.enums.EstadoReservacion;
import com.berracos.barber.models.Barbero;
import com.berracos.barber.models.Servicio;
import com.berracos.barber.models.Reservacion;
import com.berracos.barber.repositories.BarberoRepository;
import com.berracos.barber.repositories.ServicioRepository;
import com.berracos.barber.repositories.ReservacionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReservacionService {

    private final ReservacionRepository reservacionRepository;
    private final BarberoRepository barberoRepository;
    private final ServicioRepository servicioRepository;

    public ReservacionService(
            ReservacionRepository reservacionRepository,
            BarberoRepository barberoRepository,
            ServicioRepository servicioRepository
    ) {
        this.reservacionRepository = reservacionRepository;
        this.barberoRepository = barberoRepository;
        this.servicioRepository = servicioRepository;
    }

    @Transactional(readOnly = true)
    public List<ReservacionDTO> obtenerTodas() {
        return reservacionRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ReservacionDTO> obtenerPorBarberoYPeriodo(
            Integer barberoId,
            OffsetDateTime inicio,
            OffsetDateTime fin
    ) {
        return reservacionRepository
                .findByBarbero_IdAndFechaBetweenAndEstado(
                        barberoId,
                        inicio,
                        fin,
                        EstadoReservacion.PENDIENTE
                )
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    @Transactional
    public ReservacionDTO crearReservacion(ReservacionDTO dto) {

        Barbero barbero = barberoRepository.findById(dto.getBarberoId())
                .orElseThrow(() -> new RuntimeException(
                        "No se encontró el barbero seleccionado"
                ));

        Servicio servicio = servicioRepository.findById(dto.getServicioId())
                .orElseThrow(() -> new RuntimeException(
                        "No se encontró el servicio seleccionado"
                ));

        // Validar que la fecha y la hora sean correctas
        if (dto.getFecha() == null || dto.getHora() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La fecha y la hora son obligatorias"
            );
        }

        LocalDate fechaNueva = dto.getFecha().toLocalDate();
        LocalTime horaNueva;

        try {
            horaNueva = LocalTime.parse(dto.getHora());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El formato de la hora no es válido"
            );
        }

        int inicioNuevo = horaNueva.getHour() * 60
                + horaNueva.getMinute();

        int finNuevo = inicioNuevo + servicio.getDuracion();

        // Consultar las reservaciones pendientes del mismo barbero y día
        List<Reservacion> reservacionesDelDia =
                reservacionRepository.findByBarbero_IdAndFechaAndEstado(
                        barbero.getId(),
                        dto.getFecha(),
                        EstadoReservacion.PENDIENTE
                );

        // Comprobar si existe algún horario que se empalme
        boolean horarioOcupado = reservacionesDelDia.stream().anyMatch(r -> {

            LocalTime horaExistente = LocalTime.parse(r.getHora());

            int inicioExistente = horaExistente.getHour() * 60
                    + horaExistente.getMinute();

            int finExistente = inicioExistente
                    + r.getServicio().getDuracion();

            return inicioNuevo < finExistente
                    && finNuevo > inicioExistente;
        });

        if (horarioOcupado) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "El horario seleccionado ya está ocupado. Elige otro horario."
            );
        }

        Reservacion reservacion = new Reservacion();

        reservacion.setNombreCliente(dto.getNombreCliente());
        reservacion.setTelefono(dto.getTelefono());
        reservacion.setFecha(dto.getFecha());
        reservacion.setHora(dto.getHora());
        reservacion.setEstado(EstadoReservacion.PENDIENTE);
        reservacion.setBarbero(barbero);
        reservacion.setServicio(servicio);

        Reservacion guardada = reservacionRepository.save(reservacion);

        return convertirADTO(guardada);
    }

    @Transactional
    public ReservacionDTO cambiarEstado(
            Integer id,
            EstadoReservacion nuevoEstado
    ) {
        Reservacion reservacion = reservacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "No se encontró la reservación con ID: " + id
                ));

        reservacion.setEstado(nuevoEstado);

        Reservacion guardada = reservacionRepository.save(reservacion);

        return convertirADTO(guardada);
    }

    private ReservacionDTO convertirADTO(Reservacion reservacion) {
        ReservacionDTO dto = new ReservacionDTO();

        dto.setId(reservacion.getId());
        dto.setNombreCliente(reservacion.getNombreCliente());
        dto.setTelefono(reservacion.getTelefono());
        dto.setFecha(reservacion.getFecha());
        dto.setHora(reservacion.getHora());
        dto.setEstado(reservacion.getEstado());

        dto.setBarberoId(reservacion.getBarbero().getId());
        dto.setNombreBarbero(reservacion.getBarbero().getNombre());

        dto.setServicioId(reservacion.getServicio().getId());
        dto.setNombreServicio(reservacion.getServicio().getNombre());
        dto.setPrecio(reservacion.getServicio().getPrecio().doubleValue());
        dto.setDuracion(reservacion.getServicio().getDuracion());

        return dto;
    }
}