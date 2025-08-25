package com.pruebatecnica.medicamentos.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pruebatecnica.lib.dto.HttpGlobalResponseDTO;
import com.pruebatecnica.lib.dto.PaginationResponseDTO;
import com.pruebatecnica.lib.entity.Medicamento;
import com.pruebatecnica.lib.enums.MedicamentoEstadoEnum;
import com.pruebatecnica.lib.repository.MedicamentoRepository;
import com.pruebatecnica.lib.utils.ValidatorUtil;
import com.pruebatecnica.medicamentos.dto.CrearMedicamentoRequestDTO;
import com.pruebatecnica.medicamentos.dto.EditarMedicamentoRequestDTO;

@Service
public class MedicamentosService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private ValidatorUtil validatorUtil;

    public HttpGlobalResponseDTO<PaginationResponseDTO<Medicamento>> obtenerMedicamentos(String pagina, String cantidadRegistros) {

        Pageable pageable = PageRequest.of(Integer.parseInt(pagina), Integer.parseInt(cantidadRegistros));
        Page<Medicamento> medicamentos = medicamentoRepository.findAllByEstadoOrderByFechaCreacionDesc(MedicamentoEstadoEnum.ACTIVO.getValor(), pageable);
        
        HttpGlobalResponseDTO<PaginationResponseDTO<Medicamento>> response = new HttpGlobalResponseDTO<>();
        PaginationResponseDTO<Medicamento> data = new PaginationResponseDTO<>();
        data.setContent(medicamentos.getContent());
        data.setTotalElements(Long.valueOf(medicamentos.getTotalElements()));
        response.setMessage("Medicamentos obtenidos correctamente");
        response.setData(data);
        return response;
    }

    public HttpGlobalResponseDTO<Medicamento> crearMedicamento(CrearMedicamentoRequestDTO medicamento) throws Exception {
        String error = validatorUtil.validate(CrearMedicamentoRequestDTO.class, medicamento);

        if (error != null) {
            throw new Exception(error);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime fechaFabricacion = LocalDate.parse(medicamento.getFechaFabricacion(), formatter).atStartOfDay();
        LocalDateTime fechaVencimiento = LocalDate.parse(medicamento.getFechaVencimiento(), formatter).atStartOfDay();
        
        Medicamento medicamentoEntity = new Medicamento();
        medicamentoEntity.setNombre(medicamento.getNombre());
        medicamentoEntity.setLaboratorio(medicamento.getLaboratorio());
        medicamentoEntity.setFechaFabricacion(fechaFabricacion);
        medicamentoEntity.setFechaVencimiento(fechaVencimiento);
        medicamentoEntity.setCantidadStock(medicamento.getCantidad());
        medicamentoEntity.setValorUnitario(medicamento.getValorUnitario());
        medicamentoEntity.setFechaCreacion(LocalDateTime.now());
        medicamentoEntity.setFechaActualizacion(LocalDateTime.now());
        medicamentoRepository.save(medicamentoEntity);

        HttpGlobalResponseDTO<Medicamento> response = new HttpGlobalResponseDTO<>();
        response.setMessage("Medicamento creado correctamente");
        return response;
    }

    public HttpGlobalResponseDTO<Medicamento> editarMedicamento(EditarMedicamentoRequestDTO medicamento) throws Exception {
        String error = validatorUtil.validate(EditarMedicamentoRequestDTO.class, medicamento);

        if (error != null) {
            throw new Exception(error);
        }

        HttpGlobalResponseDTO<Medicamento> response = new HttpGlobalResponseDTO<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Optional<Medicamento> medicamentoEntity = medicamentoRepository.findByIdAndEstado(medicamento.getId(), MedicamentoEstadoEnum.ACTIVO.getValor());
        if (medicamentoEntity.isEmpty()) {
            throw new Exception("Medicamento no encontrado");
        }

        medicamentoEntity.get().setNombre(medicamento.getNombre());
        medicamentoEntity.get().setLaboratorio(medicamento.getLaboratorio());
        medicamentoEntity.get().setFechaFabricacion(LocalDate.parse(medicamento.getFechaFabricacion(), formatter).atStartOfDay());
        medicamentoEntity.get().setFechaVencimiento(LocalDate.parse(medicamento.getFechaVencimiento(), formatter).atStartOfDay());
        medicamentoEntity.get().setCantidadStock(medicamento.getCantidad());
        medicamentoEntity.get().setValorUnitario(medicamento.getValorUnitario());
        medicamentoEntity.get().setFechaActualizacion(LocalDateTime.now());
        medicamentoRepository.save(medicamentoEntity.get());

        response.setMessage("Medicamento actualizado correctamente");
        return response;
    }

    public HttpGlobalResponseDTO<Medicamento> eliminarMedicamento(String idMedicamento) throws Exception {

        Optional<Medicamento> medicamentoEntity = medicamentoRepository.findByIdAndEstado(Long.valueOf(idMedicamento), MedicamentoEstadoEnum.ACTIVO.getValor());
        
        if (medicamentoEntity.isEmpty()) {
            throw new Exception("Medicamento no encontrado");
        }
        
        medicamentoEntity.get().setEstado(MedicamentoEstadoEnum.INACTIVO.getValor());
        medicamentoRepository.save(medicamentoEntity.get());

        HttpGlobalResponseDTO<Medicamento> response = new HttpGlobalResponseDTO<>();
        response.setMessage("Medicamento eliminado correctamente");
        return response;
    }

}