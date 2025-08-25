package com.pruebatecnica.lib.enums;

public enum MedicamentoEstadoEnum {
    ACTIVO(1L),
    INACTIVO(0L);

    private final Long valor;

    private MedicamentoEstadoEnum(Long valor) {
        this.valor = valor;
    }

    public Long getValor() {
        return valor;
    }
}
