package com.pruebatecnica.ventas.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CrearVentasRequestDTO {

    /**
     * Id del medicamento
     */
    @Min(1)
    @NotNull
    private Long idMedicamento;

    /**
     * Cantidad de medicamento
     */
    @Min(1)
    @NotNull
    private Long cantidadVenta;

    /**
     * Valor unitario del medicamento
     */
    @Min(1)
    @NotNull
    private Long valorUnitario;

    /**
     * Valor total de la venta
     */
    @Min(1)
    @NotNull
    private Long valorTotal;
}
