package com.GerenciamentoHP.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.GerenciamentoHP.Model.PacientePerfil;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PacientePerfilRepository extends JpaRepository<PacientePerfil,Long>, JpaSpecificationExecutor<PacientePerfil> {
    
    Optional<PacientePerfil> findById(Long atendimento);

    List<PacientePerfil>findByAtendimento(Long atendimento);

    Optional<PacientePerfil> findByrg(String rg);

    List<PacientePerfil> findByNome(String nome);

    List<PacientePerfil> findByNomeAndAtendimento(String nome, Long atendimento);
}
