package com.pruebatecnica.lib.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "VENTAS")
@Data
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_VENTA")
    private Long id;

    @Column(name = "FECHA_HORA")
    private LocalDateTime fechaHora;

    @Column(name = "ID_MEDICAMENTO")
    private Long idMedicamento;

    @Column(name = "CANTIDAD")
    private Long cantidadVenta;

    @Column(name = "VALOR_UNITARIO")
    private Long valorUnitario;

    @Column(name = "VALOR_TOTAL")
    private Long valorTotal;

    @PrePersist
    protected void onCreate() {
        this.fechaHora = LocalDateTime.now();
    }
}