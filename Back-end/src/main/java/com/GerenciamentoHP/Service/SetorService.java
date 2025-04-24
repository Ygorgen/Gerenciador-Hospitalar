package com.GerenciamentoHP.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.GerenciamentoHP.Model.Setor;
import com.GerenciamentoHP.Repository.PacientePerfilRepository;
import com.GerenciamentoHP.Repository.SetorRepository;

@Service
public class SetorService {

    @Autowired
    PacientePerfilRepository pacientePerfilRepository;

    @Autowired
    SetorRepository setorRepository;

    public Setor salvarSetor(Setor setor) {
        return setorRepository.save(setor);
    }

    public List<Setor> listarTodosSetores() {
        return setorRepository.findAll();
    }

    public ResponseEntity<Setor> buscarSetorPorId(Long id) {
        return setorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Setor> atualizarSetor(Setor setor) {
        if (setor.getId() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return setorRepository.findById(setor.getId()).map(setorExistente -> {
            setorExistente.setNome(setor.getNome());

            setorRepository.save(setorExistente);
            return ResponseEntity.ok(setorExistente);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public void deletarSetor(Long id) {
        Optional<Setor> setorOp = setorRepository.findById(id);

        if (setorOp.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Setor Não Encontrado");
        }
        setorRepository.delete(setorOp.get());
    }
}
