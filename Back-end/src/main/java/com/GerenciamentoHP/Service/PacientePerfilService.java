package com.GerenciamentoHP.Service;

import java.util.List;
import java.util.Optional;

import com.GerenciamentoHP.DTO.RespostaErro;
import com.GerenciamentoHP.Exceptions.RegistroDuplicadoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.GerenciamentoHP.DTO.PacientePerfilDto;
import com.GerenciamentoHP.Model.PacientePerfil;
import com.GerenciamentoHP.Repository.PacientePerfilRepository;
import com.GerenciamentoHP.Repository.SetorRepository;

@Service
public class PacientePerfilService {

    @Autowired
    private PacientePerfilRepository pacientePerfilRepository;

    @Autowired
    SetorRepository setorRepository;

    public ResponseEntity<?> salvarPerfil(PacientePerfilDto pacientePerfilDto) {
        try {
            PacientePerfil paciente = pacientePerfilDto.mapearPacientePerfil();

            Optional<PacientePerfil> pacienteExistente = pacientePerfilRepository.findByrg(paciente.getRg());

            if (pacienteExistente.isPresent()) {
                throw new RegistroDuplicadoException("Paciente com esse RG já existe.");
            }

            PacientePerfil pacienteSalvo = pacientePerfilRepository.save(paciente);
            return ResponseEntity.status(HttpStatus.CREATED).body(pacienteSalvo);

        } catch (RegistroDuplicadoException e) {
            var erroDTO = RespostaErro.conflito(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(erroDTO);
        }
    }


    public List<PacientePerfil> verTodosPacientes() {
        return pacientePerfilRepository.findAll();
    }

    public ResponseEntity<PacientePerfil> buscarPorRg(Integer rg) {
        return pacientePerfilRepository.findByrg(rg)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public Optional<PacientePerfil> findByAtendimento(Long atendimento) {
        return pacientePerfilRepository.findById(atendimento);
    }

    public ResponseEntity<PacientePerfil> atualizarPacientePerfil(PacientePerfil pacientePerfil) {
        if (pacientePerfil.getAtendimento() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        return pacientePerfilRepository.findById(pacientePerfil.getAtendimento())
                .map(existente -> {

                    existente.setNome(pacientePerfil.getNome());
                    existente.setRg(pacientePerfil.getRg());
                    existente.setPlano(pacientePerfil.getPlano());

                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(pacientePerfilRepository.save(existente));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public void deletarPaciente(Long id) {
        Optional<PacientePerfil> paciente = pacientePerfilRepository.findById(id);

        if (paciente.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Paciente Não Encontrado!", null);
        }
        pacientePerfilRepository.delete(paciente.get());
    }

}
