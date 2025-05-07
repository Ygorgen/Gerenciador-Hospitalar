package com.GerenciamentoHP.Model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_ficha_paciente")
@EntityListeners(AuditingEntityListener.class)
public class FichaPaciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer leito;

    private String plano;

    @NotBlank
    private String diagnostico;

    private LocalDate dataInternacao;

    @CreatedDate
    private LocalDate inicioAtendimento;

    private LocalDate fimAtendimento;

    private String usoO2;

    private LocalDate alta;

    private LocalDate obito;

    private String observacao;

    @ManyToOne
    @JsonIgnoreProperties("fichaPaciente")
    private PacientePerfil pacientePerfil;

    @ManyToOne
    @JsonIgnoreProperties("fichasPacientes")
    private Setor setor;
}
