package com.GerenciamentoHP.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GerenciamentoHP.Model.FichaPaciente;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FichaPacienteRepository extends JpaRepository<FichaPaciente,Long>, JpaSpecificationExecutor<FichaPaciente> {
    
}
