package com.GerenciamentoHP.Controller.common;

import com.GerenciamentoHP.Controller.DTO.CampodeErros;
import com.GerenciamentoHP.Controller.DTO.RespostaErro;
import com.GerenciamentoHP.Exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
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

    @ExceptionHandler(RegistroDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public RespostaErro handleRegistroDuplicadoException(RegistroDuplicadoException e) {
        return RespostaErro.conflito(e.getMessage());
    }

    @ExceptionHandler(OperacaoNaoPermitidaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RespostaErro handleOperacaoNaoPermitidaException(OperacaoNaoPermitidaException e) {
        return RespostaErro.respostaPadrao(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RespostaErro handleErroNaoTratados(RuntimeException e){
        return new RespostaErro(HttpStatus.INTERNAL_SERVER_ERROR.value(),"ERRO INESPERADO. ENTRE EM CONTATO PARA OBTER MAIS INFORMAÇÕES!"
                ,List.of());

    }

    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public RespostaErro InvalidJwtAuthenticationException(RuntimeException e){
        return new RespostaErro(HttpStatus.FORBIDDEN.value(),"Authentication Inválid!"
                ,List.of());

    }

    @ExceptionHandler(RequiredObjectIsNullException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RespostaErro RequiredObjectIsNullException(RequiredObjectIsNullException e) {
        return RespostaErro.respostaPadrao(e.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public RespostaErro handleBadCredentialsException(BadCredentialsException e) {
        return new RespostaErro(HttpStatus.FORBIDDEN.value(), "Usuário inexistente ou senha inválida!", List.of());
    }

    @ExceptionHandler(FileNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public RespostaErro handleFileNotFoundlException(BadCredentialsException e) {
        return new RespostaErro(HttpStatus.NOT_FOUND.value(), "Arquivo não encontrado!", List.of());
    }

    @ExceptionHandler(FileStorageException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RespostaErro handleFileStorageException(BadCredentialsException e) {
        return new RespostaErro(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro ao armazenar o arquivo!", List.of());
    }
}
