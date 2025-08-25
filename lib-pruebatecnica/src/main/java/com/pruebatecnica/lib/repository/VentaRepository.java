package com.pruebatecnica.lib.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pruebatecnica.lib.entity.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    @Query(value = "SELECT * FROM VENTAS WHERE TRUNC(FECHA_HORA) BETWEEN :fechaInicio AND :fechaFin", nativeQuery = true)
    Page<Venta> findByFechaHoraBetween(LocalDate fechaInicio, LocalDate fechaFin, Pageable pageable);
}
