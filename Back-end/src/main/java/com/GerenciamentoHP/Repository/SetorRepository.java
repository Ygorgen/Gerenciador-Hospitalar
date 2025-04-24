package com.GerenciamentoHP.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.GerenciamentoHP.Model.Setor;

public interface SetorRepository extends JpaRepository<Setor, Long> {

    List<Setor> findAllByNomeContainingIgnoreCase(String nome);
}
