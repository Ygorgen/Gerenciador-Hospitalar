package com.GerenciamentoHP.Controller.DTO;

import java.time.LocalDate;

public record ResultadoPesquisaPacienteDTO(
                                           String nome,
                                           Long atendimento,
                                           String rg,
                                           LocalDate dataNascimento,
                                           LocalDate dataCadastro,
                                           LocalDate ultimaConsulta) {
}
