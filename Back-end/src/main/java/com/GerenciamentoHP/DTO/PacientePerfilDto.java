package com.GerenciamentoHP.DTO;

import java.time.LocalDate;

import com.GerenciamentoHP.Model.PacientePerfil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
