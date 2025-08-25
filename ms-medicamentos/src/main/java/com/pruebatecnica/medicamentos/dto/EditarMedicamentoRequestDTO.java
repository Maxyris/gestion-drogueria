package com.pruebatecnica.medicamentos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EditarMedicamentoRequestDTO extends CrearMedicamentoRequestDTO {
    /**
     * id del medicamento
     */
    @Min(1)
    @NotNull
    private Long id;

}
