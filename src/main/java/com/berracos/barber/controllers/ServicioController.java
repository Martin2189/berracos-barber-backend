package com.berracos.barber.controllers;

import com.berracos.barber.dtos.ServicioDTO;
import com.berracos.barber.services.ServicioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @GetMapping
    public List<ServicioDTO> obtenerActivos() {
        return servicioService.obtenerActivos();
    }
}