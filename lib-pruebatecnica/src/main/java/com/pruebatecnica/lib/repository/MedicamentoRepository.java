package com.pruebatecnica.lib.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pruebatecnica.lib.entity.Medicamento;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

    Page<Medicamento> findAllByEstadoOrderByFechaCreacionDesc(Long estado, Pageable pageable);

    Optional<Medicamento> findByIdAndEstado(Long id, Long estado);
}