package com.berracos.barber.services;

import com.berracos.barber.dtos.BarberoDTO;
import com.berracos.barber.models.Barbero;
import com.berracos.barber.repositories.BarberoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarberoService {

    private final BarberoRepository barberoRepository;

    public BarberoService(BarberoRepository barberoRepository) {
        this.barberoRepository = barberoRepository;
    }

    public List<BarberoDTO> obtenerActivos() {
        return barberoRepository.findByActivoTrue()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    public List<BarberoDTO> obtenerTodos() {
        return barberoRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    private BarberoDTO convertirADTO(Barbero barbero) {
        return new BarberoDTO(
                barbero.getId(),
                barbero.getNombre(),
                barbero.getActivo()
        );
    }
}