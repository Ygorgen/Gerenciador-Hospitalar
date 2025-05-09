package com.GerenciamentoHP.DTO;

import java.time.LocalDate;

import com.GerenciamentoHP.Model.PacientePerfil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PacientePerfilDto(

        @NotBlank(message = "NOME É UM CAMPO OBRIGATÓRIO")
        String nome,

        @NotBlank(message = "RG É UM CAMPO OBRIGATÓRIO.")
        @Size(min = 10, max = 11, message = "O RG DEVE CONTER ENTRE 10 E 11 NÚMEROS")
        String rg,

        LocalDate dataNascimento
) {

    public PacientePerfil mapearPacientePerfil() {
        PacientePerfil paciente = new PacientePerfil();
        paciente.setNome(this.nome());
        paciente.setRg(this.rg());
        paciente.setDataNascimento(this.dataNascimento());
        return paciente;
    }

}
