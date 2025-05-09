package com.GerenciamentoHP.Controller.common;

import com.GerenciamentoHP.DTO.CampodeErros;
import com.GerenciamentoHP.DTO.RespostaErro;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public RespostaErro handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<CampodeErros> listaDeErros = fieldErrors.stream().map(fe -> new CampodeErros(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());
        return new RespostaErro(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de Validação",
                listaDeErros);
    }
}
