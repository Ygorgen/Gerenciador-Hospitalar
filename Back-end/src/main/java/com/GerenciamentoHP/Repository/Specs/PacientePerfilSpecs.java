package com.GerenciamentoHP.Repository.Specs;

import com.GerenciamentoHP.Model.PacientePerfil;
import org.springframework.data.jpa.domain.Specification;

public class PacientePerfilSpecs {

    public static Specification<PacientePerfil>atendimentoEqual(Long atendimento){
        return (root,query,cb)->cb.equal(root.get("atendimento"),atendimento);
    }

    public static Specification<PacientePerfil>nomelike(String nome){
        return (root, query, cb)->cb.like(cb.upper(root.get("nome"))
                ,"%"+nome.toUpperCase()+"%");
    }
}
