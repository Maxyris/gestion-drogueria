package com.pruebatecnica.lib.utils;

import java.util.Set;

import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

@Service
public class ValidatorUtil {
    public ValidatorUtil() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    private final Validator validator;

    /**
     * Realiza la ejecución de validaciones de los campos de un DTO
     * 
     * @param classDto clase DTO
     * @param object   Campos del DTO
     */
    public <T> String validate(Class<T> classDto, Object object) {
        Set<ConstraintViolation<Object>> violations = validator
                .validate(object);

        if (!violations.isEmpty()) {
            return violations.stream().findFirst().get().getPropertyPath().toString().concat(": ")
                    .concat(violations.stream()
                            .findFirst().get().getMessage());
        }

        return null;
    }
}