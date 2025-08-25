package com.pruebatecnica.medicamentos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pruebatecnica.lib.dto.HttpGlobalResponseDTO;
import com.pruebatecnica.lib.dto.PaginationResponseDTO;
import com.pruebatecnica.lib.entity.Medicamento;
import com.pruebatecnica.medicamentos.dto.CrearMedicamentoRequestDTO;
import com.pruebatecnica.medicamentos.dto.EditarMedicamentoRequestDTO;
import com.pruebatecnica.medicamentos.services.MedicamentosService;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentosController {

    @Autowired
    private MedicamentosService medicamentosService;

    @GetMapping
    public HttpGlobalResponseDTO<PaginationResponseDTO<Medicamento>> obtenerMedicamentos(@RequestParam String pagina,
            @RequestParam String cantidadRegistros) {
        return medicamentosService.obtenerMedicamentos(pagina, cantidadRegistros);
    }

    @PostMapping
    public HttpGlobalResponseDTO<Medicamento> crearMedicamento(@RequestBody CrearMedicamentoRequestDTO medicamento)
            throws Exception {
        return medicamentosService.crearMedicamento(medicamento);
    }

    @PutMapping
    public HttpGlobalResponseDTO<Medicamento> editarMedicamento(@RequestBody EditarMedicamentoRequestDTO medicamento)
            throws Exception {
        return medicamentosService.editarMedicamento(medicamento);
    }

    @DeleteMapping
    public HttpGlobalResponseDTO<Medicamento> eliminarMedicamento(@RequestParam String idMedicamento)
            throws Exception {
        return medicamentosService.eliminarMedicamento(idMedicamento);
    }

}