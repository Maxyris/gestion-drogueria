package com.pruebatecnica.ventas.dto;

import com.pruebatecnica.lib.entity.Venta;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ObtenerVentasResponseDTO extends Venta {
    private String nombreMedicamento;
}
