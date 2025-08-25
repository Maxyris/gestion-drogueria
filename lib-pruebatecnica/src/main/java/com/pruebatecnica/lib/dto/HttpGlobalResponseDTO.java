package com.pruebatecnica.lib.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpGlobalResponseDTO<T> {
    /**
     * Mensaje de la respuesta
     */
    private String message;

    /**
     * Datos de la respuesta
     */
    private T data;

}
