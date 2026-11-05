package com.berracos.barber.repositories;

import com.berracos.barber.models.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicioRepository extends JpaRepository<Servicio, Integer> {

    List<Servicio> findByActivoTrue();
}
