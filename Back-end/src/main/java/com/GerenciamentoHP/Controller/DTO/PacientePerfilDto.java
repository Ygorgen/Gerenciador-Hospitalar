package com.GerenciamentoHP.Controller.DTO;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record PacientePerfilDto(

        @NotBlank(message = "NOME É UM CAMPO OBRIGATÓRIO.")
        @Size(min = 3, max = 100, message = "CAMPO FORA DO TAMANHO PADRÃO.")
        String nome,

        @CPF
        @NotBlank(message = "RG É UM CAMPO OBRIGATÓRIO.")
        String rg,

        @Past(message = "INSIRA UM DATA VÁLIDA.")
        LocalDate dataNascimento) {
}
