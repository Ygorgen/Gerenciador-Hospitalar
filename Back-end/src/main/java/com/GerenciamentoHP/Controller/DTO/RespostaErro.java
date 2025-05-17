package com.GerenciamentoHP.Controller.DTO;

import org.springframework.http.HttpStatus;

import java.util.List;

public record RespostaErro(int status, String mensagem, List<CampodeErros> erros) {

    public static RespostaErro respostaPadrao(String mensagem) {
        return new RespostaErro(HttpStatus.BAD_REQUEST.value(), mensagem, List.of());
    }

    public static RespostaErro conflito(String mensagem) {
        return new RespostaErro(HttpStatus.CONFLICT.value(), mensagem, List.of());
    }
}
