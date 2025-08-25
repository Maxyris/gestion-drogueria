package com.pruebatecnica.lib.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaginationResponseDTO<T> {
    /**
     * Lista de elementos
     */
    private List<T> content;

    /**
     * Total de elementos
     */
    private Long totalElements;

}
