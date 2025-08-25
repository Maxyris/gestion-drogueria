package com.pruebatecnica.ventas.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pruebatecnica.lib.dto.HttpGlobalResponseDTO;
import com.pruebatecnica.lib.dto.PaginationResponseDTO;
import com.pruebatecnica.lib.entity.Medicamento;
import com.pruebatecnica.lib.entity.Venta;
import com.pruebatecnica.lib.repository.MedicamentoRepository;
import com.pruebatecnica.lib.repository.VentaRepository;
import com.pruebatecnica.lib.utils.ValidatorUtil;
import com.pruebatecnica.ventas.dto.CrearVentasRequestDTO;
import com.pruebatecnica.ventas.dto.ObtenerVentasResponseDTO;

@Service
public class VentasService {

    @Autowired
    private VentaRepository VentaRepository;

    @Autowired
    private ValidatorUtil validatorUtil;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    public HttpGlobalResponseDTO<Venta> crearVenta(CrearVentasRequestDTO venta) throws Exception {
        String error = validatorUtil.validate(CrearVentasRequestDTO.class, venta);

        if (error != null) {
            throw new Exception(error);
        }

        Optional<Medicamento> medicamento = medicamentoRepository.findById(venta.getIdMedicamento());

        if (medicamento.isEmpty()) {
            throw new Exception("Medicamento no encontrado");
        }
        if (medicamento.get().getCantidadStock() < venta.getCantidadVenta()) {
            throw new Exception("Cantidad de medicamento insuficiente");
        }

        if (!venta.getValorUnitario().equals(medicamento.get().getValorUnitario())) {
            throw new Exception("Valor unitario no coincide con el valor unitario del medicamento");
        }

        if (!venta.getValorTotal().equals(venta.getCantidadVenta() * venta.getValorUnitario())) {
            throw new Exception("El valor total no coincide con el valor unitario y la cantidad");
        }

        Venta VentaEntity = new Venta();
        VentaEntity.setIdMedicamento(venta.getIdMedicamento());
        VentaEntity.setCantidadVenta(venta.getCantidadVenta());
        VentaEntity.setValorUnitario(venta.getValorUnitario());
        VentaEntity.setValorTotal(venta.getValorTotal());
        VentaRepository.save(VentaEntity);

        medicamento.get().setCantidadStock(medicamento.get().getCantidadStock() - venta.getCantidadVenta());
        medicamentoRepository.save(medicamento.get());

        HttpGlobalResponseDTO<Venta> response = new HttpGlobalResponseDTO<>();
        response.setMessage("Venta creada correctamente");
        return response;
    }

    public HttpGlobalResponseDTO<PaginationResponseDTO<ObtenerVentasResponseDTO>> obtenerVentasFiltroFecha(String fechaInicio, String fechaFin, String pagina, String cantidadRegistros) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaInicioParam = LocalDate.parse(fechaInicio, formatter);
        LocalDate fechaFinParam = LocalDate.parse(fechaFin, formatter);

        Pageable pageable = PageRequest.of(Integer.parseInt(pagina), Integer.parseInt(cantidadRegistros));
        Page<Venta> ventas = VentaRepository.findByFechaHoraBetween(fechaInicioParam, fechaFinParam, pageable);

        HttpGlobalResponseDTO<PaginationResponseDTO<ObtenerVentasResponseDTO>> response = new HttpGlobalResponseDTO<>();
        PaginationResponseDTO<ObtenerVentasResponseDTO> data = new PaginationResponseDTO<ObtenerVentasResponseDTO>();
        data.setContent(ventas.getContent().stream().map(venta -> {
            ObtenerVentasResponseDTO ventaResponse = new ObtenerVentasResponseDTO();
            ventaResponse.setId(venta.getId());
            ventaResponse.setFechaHora(venta.getFechaHora());
            ventaResponse.setCantidadVenta(venta.getCantidadVenta());
            ventaResponse.setValorUnitario(venta.getValorUnitario());
            ventaResponse.setValorTotal(venta.getValorTotal());

            Optional<Medicamento> medicamento = medicamentoRepository.findById(venta.getIdMedicamento());

            if (medicamento.isEmpty()) {
                return null;
            }

            ventaResponse.setNombreMedicamento(medicamento.get().getNombre());
            
            return ventaResponse;
        }).collect(Collectors.toList()));
        data.setTotalElements(Long.valueOf(ventas.getTotalElements()));
        response.setMessage("Ventas obtenidas correctamente");
        response.setData(data);
        return response;
    }

}