package com.pruebatecnica.medicamentos.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CrearMedicamentoRequestDTO {
    /**
     * Nombre del medicamento
     */
    @NotNull
    @Length(min = 1)
    private String nombre;

    /**
     * Laboratorio fabricante del medicamento
     */
    @NotNull
    @Length(min = 1)
    private String laboratorio;

    /**
     * Fecha de fabricación del medicamento
     */
    @NotNull
    @Length(max = 19)
    private String fechaFabricacion;

    /**
     * Fecha de vencimiento del medicamento
     */
    @NotNull
    @Length(max = 19)
    private String fechaVencimiento;

    /**
     * Cantidad de medicamento
     */
    @Min(1)
    @NotNull
    private Long cantidad;

    /**
     * Valor de la unidad del medicamento
     */
    @Min(1)
    @NotNull
    private Long valorUnitario;
}
