package com.berracos.barber.repositories;

import com.berracos.barber.enums.EstadoReservacion;
import com.berracos.barber.models.Reservacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface ReservacionRepository extends JpaRepository<Reservacion, Integer> {

    List<Reservacion> findByBarbero_IdAndFechaBetweenAndEstado(
            Integer barberoId,
            OffsetDateTime inicio,
            OffsetDateTime fin,
            EstadoReservacion estado
    );

    List<Reservacion> findByBarbero_IdAndFechaAndEstado(
            Integer barberoId,
            OffsetDateTime fecha,
            EstadoReservacion estado
    );
}