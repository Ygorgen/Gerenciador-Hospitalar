package com.GerenciamentoHP.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.GerenciamentoHP.Model.FichaPaciente;
import com.GerenciamentoHP.Model.PacientePerfil;
import com.GerenciamentoHP.Repository.FichaPacienteRepository;
import com.GerenciamentoHP.Repository.PacientePerfilRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class FichaPacienteService {

    @Autowired
    FichaPacienteRepository fichaPacienteRepository;

    @Autowired
    PacientePerfilRepository pacientePerfilRepository;

    public FichaPaciente salvarFichaPaciente(FichaPaciente fichaPaciente) {
        if (fichaPaciente.getPacientePerfil() == null || fichaPaciente.getPacientePerfil().getAtendimento() == null) {
            throw new IllegalArgumentException("O Paciente Precisa Estar Cadastrado.");
        }
        PacientePerfil perfil = pacientePerfilRepository
                .findById(fichaPaciente.getPacientePerfil().getAtendimento())
                .orElseThrow(() -> new EntityNotFoundException("PacientePerfil não encontrado."));

        fichaPaciente.setPacientePerfil(perfil);
        return fichaPacienteRepository.save(fichaPaciente);
    }

    public List<FichaPaciente> verTodasFichas() {
        return fichaPacienteRepository.findAll();
    }


    public void deletarFicha(Long id) {
        Optional<FichaPaciente> ficha = fichaPacienteRepository.findById(id);

        if (ficha.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ficha Não Encontrada.", null);
        }
        fichaPacienteRepository.delete(ficha.get());
    }

    public ResponseEntity<FichaPaciente> atualizarFicha(FichaPaciente fichaPaciente) {
        if (fichaPaciente.getId() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return fichaPacienteRepository.findById(fichaPaciente.getId())
                .map(existente -> {

                    existente.setLeito(fichaPaciente.getLeito());
                    existente.setPlano(fichaPaciente.getPlano());
                    existente.setDiagnostico(fichaPaciente.getDiagnostico());
                    existente.setDataInternacao(fichaPaciente.getDataInternacao());
                    existente.setFimAtendimento(fichaPaciente.getFimAtendimento());
                    existente.setUsoO2(fichaPaciente.getUsoO2());
                    existente.setAlta(fichaPaciente.getAlta());
                    existente.setObito(fichaPaciente.getObito());
                    existente.setObservacao(fichaPaciente.getObservacao());

                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(fichaPacienteRepository.save(existente));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
