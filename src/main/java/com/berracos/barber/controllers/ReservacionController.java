package com.berracos.barber.controllers;

import com.berracos.barber.dtos.ReservacionDTO;
import com.berracos.barber.enums.EstadoReservacion;
import com.berracos.barber.services.ReservacionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reservaciones")
public class ReservacionController {

    private final ReservacionService reservacionService;

    public ReservacionController(ReservacionService reservacionService) {
        this.reservacionService = reservacionService;
    }

    @GetMapping
    public List<ReservacionDTO> obtenerTodas() {
        return reservacionService.obtenerTodas();
    }

    @PostMapping
    public ReservacionDTO crearReservacion(@RequestBody ReservacionDTO dto) {
        return reservacionService.crearReservacion(dto);
    }

    @GetMapping("/disponibilidad")
    public List<ReservacionDTO> obtenerDisponibilidad(
            @RequestParam Integer barberoId,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            OffsetDateTime inicio,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            OffsetDateTime fin
    ) {
        return reservacionService.obtenerPorBarberoYPeriodo(
                barberoId,
                inicio,
                fin
        );
    }

    @PutMapping("/{id}/estado")
    public ReservacionDTO cambiarEstado(
            @PathVariable Integer id,
            @RequestBody EstadoReservacion nuevoEstado
    ) {
        return reservacionService.cambiarEstado(id, nuevoEstado);
    }
}


