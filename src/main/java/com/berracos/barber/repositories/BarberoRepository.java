package com.berracos.barber.repositories;

import com.berracos.barber.models.Barbero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BarberoRepository extends JpaRepository<Barbero, Integer> {

    List<Barbero> findByActivoTrue();
}
