package com.GerenciamentoHP.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GerenciamentoHP.Model.FichaPaciente;

public interface FichaPacienteRepository extends JpaRepository<FichaPaciente,Long> {
    
}
