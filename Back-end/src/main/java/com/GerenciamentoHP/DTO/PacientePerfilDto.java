package com.GerenciamentoHP.DTO;

import java.time.LocalDate;

import com.GerenciamentoHP.Model.PacientePerfil;

public record PacientePerfilDto(
                String nome,
                Integer rg,
                LocalDate dataNascimento,
                String plano
                ) {

        public PacientePerfil mapearPacientePerfil() {
                PacientePerfil paciente = new PacientePerfil();
                paciente.setNome(this.nome());
                paciente.setRg(this.rg());
                paciente.setDataNascimento(this.dataNascimento());
                paciente.setPlano(this.plano());
                return paciente;
        }

}
