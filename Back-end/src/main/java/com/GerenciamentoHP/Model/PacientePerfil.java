package com.GerenciamentoHP.Model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_paciente_perfil")
@EntityListeners(AuditingEntityListener.class)
public class PacientePerfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long atendimento;

    @NotBlank
    private String nome;

    private Integer rg;

    private LocalDate dataNascimento;

    private String plano;

    @CreatedDate
    private LocalDate dataCadastro;

    @LastModifiedDate
    private LocalDate ultimaConsulta;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pacientePerfil", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("pacientePerfil")
    private List<FichaPaciente> fichaPaciente;
}
