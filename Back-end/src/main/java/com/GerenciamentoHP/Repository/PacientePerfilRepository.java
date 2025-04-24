package com.GerenciamentoHP.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.GerenciamentoHP.Model.PacientePerfil;

public interface PacientePerfilRepository extends JpaRepository<PacientePerfil,Long> {
    
    Optional<PacientePerfil> findById(Long atendimento);

    Optional<PacientePerfil> findByrg(Integer rg);

}
