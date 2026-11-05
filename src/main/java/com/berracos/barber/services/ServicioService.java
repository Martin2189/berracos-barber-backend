package com.berracos.barber.services;

import com.berracos.barber.dtos.ServicioDTO;
import com.berracos.barber.models.Servicio;
import com.berracos.barber.repositories.ServicioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioService {

    private final ServicioRepository servicioRepository;

    public ServicioService(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    public List<ServicioDTO> obtenerActivos() {
        return servicioRepository.findByActivoTrue()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    public List<ServicioDTO> obtenerTodos() {
        return servicioRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    private ServicioDTO convertirADTO(Servicio servicio) {
        return new ServicioDTO(
                servicio.getId(),
                servicio.getNombre(),
                servicio.getPrecio().doubleValue(),
                servicio.getDuracion(),
                servicio.getActivo(),
                servicio.getCategoria()
        );
    }
}