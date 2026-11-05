package com.berracos.barber.controllers;

import com.berracos.barber.dtos.BarberoDTO;
import com.berracos.barber.services.BarberoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/barberos")
public class BarberoController {

    private final BarberoService barberoService;

    public BarberoController(BarberoService barberoService) {
        this.barberoService = barberoService;
    }

    @GetMapping
    public List<BarberoDTO> obtenerActivos() {
        return barberoService.obtenerActivos();
    }
}