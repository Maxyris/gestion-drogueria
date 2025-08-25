package com.pruebatecnica.ventas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pruebatecnica.lib.dto.HttpGlobalResponseDTO;
import com.pruebatecnica.lib.dto.PaginationResponseDTO;
import com.pruebatecnica.lib.entity.Venta;
import com.pruebatecnica.ventas.dto.CrearVentasRequestDTO;
import com.pruebatecnica.ventas.dto.ObtenerVentasResponseDTO;
import com.pruebatecnica.ventas.services.VentasService;

@RestController
@RequestMapping("/ventas")
public class VentasController {

    @Autowired
    private VentasService VentasService;

    @PostMapping
    public HttpGlobalResponseDTO<Venta> crearVenta(@RequestBody CrearVentasRequestDTO venta) throws Exception {
        return VentasService.crearVenta(venta);
    }

    @GetMapping
    public HttpGlobalResponseDTO<PaginationResponseDTO<ObtenerVentasResponseDTO>> obtenerVentasFiltroFecha(@RequestParam String fechaInicio,
            @RequestParam String fechaFin, @RequestParam String pagina, @RequestParam String cantidadRegistros)
            throws Exception {
        return VentasService.obtenerVentasFiltroFecha(fechaInicio, fechaFin, pagina, cantidadRegistros);
    }

}