package com.pruebatecnica.lib.entity;

import java.time.LocalDateTime;

import com.pruebatecnica.lib.enums.MedicamentoEstadoEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "MEDICAMENTOS")
@Data
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MEDICAMENTO")
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "LABORATORIO")
    private String laboratorio;

    @Column(name = "FECHA_FABRICACION")
    private LocalDateTime fechaFabricacion;

    @Column(name = "FECHA_VENCIMIENTO")
    private LocalDateTime fechaVencimiento;

    @Column(name = "CANTIDAD_STOCK")
    private Long cantidadStock;

    @Column(name = "VALOR_UNITARIO")
    private Long valorUnitario;

    @Column(name = "FECHA_CREACION")
    private LocalDateTime fechaCreacion;

    @Column(name = "FECHA_ACTUALIZACION")
    private LocalDateTime fechaActualizacion;

    @Column(name = "ESTADO")
    private Long estado;

    @PrePersist
    protected void onCreate() {
        this.estado = MedicamentoEstadoEnum.ACTIVO.getValor();
    }
}
