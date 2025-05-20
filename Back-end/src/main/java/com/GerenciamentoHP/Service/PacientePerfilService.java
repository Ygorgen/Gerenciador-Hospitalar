package com.GerenciamentoHP.Service;

import java.util.List;
import java.util.Optional;

import com.GerenciamentoHP.Controller.DTO.RespostaErro;
import com.GerenciamentoHP.Controller.mappers.PacienteMapper;
import com.GerenciamentoHP.Exceptions.RegistroDuplicadoException;
import com.GerenciamentoHP.Exceptions.OperacaoNaoPermitidaException;
import com.GerenciamentoHP.Repository.Specs.PacientePerfilSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.GerenciamentoHP.Controller.DTO.PacientePerfilDto;
import com.GerenciamentoHP.Model.PacientePerfil;
import com.GerenciamentoHP.Repository.PacientePerfilRepository;
import com.GerenciamentoHP.Repository.SetorRepository;

@Service
public class PacientePerfilService {

    @Autowired
    private PacientePerfilRepository pacientePerfilRepository;

    @Autowired
    private PacienteMapper mapper;

    @Autowired
    private SetorRepository setorRepository;

    public ResponseEntity<PacientePerfil> salvarPerfil(PacientePerfilDto dto) {

        PacientePerfil paciente = mapper.toEntity(dto);

        Optional<PacientePerfil> pacienteExistente = pacientePerfilRepository.findByrg(paciente.getRg());

        if (pacienteExistente.isPresent()) {
            throw new RegistroDuplicadoException("Paciente com esse RG já existe.");
        }

        PacientePerfil pacienteSalvo = pacientePerfilRepository.save(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteSalvo);

    }

        public List<PacientePerfil>pesquisa(String nome,Long atendimento ){

            Specification<PacientePerfil> specs = Specification.where(((root, query, cb) ->cb.conjunction() ));

            if (nome != null){
                specs = specs.and(PacientePerfilSpecs.nomelike(nome));
            }
            if (atendimento!= null){
                specs =specs.and(PacientePerfilSpecs.atendimentoEqual(atendimento));
            }

            return pacientePerfilRepository.findAll(specs);
        }

    public List<PacientePerfil> verTodosPacientes() {
        return pacientePerfilRepository.findAll();
    }

    public ResponseEntity<PacientePerfil> buscarPorRg(String rg) {
        return pacientePerfilRepository.findByrg(rg)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public Optional<PacientePerfil> findByAtendimento(Long atendimento) {
        return pacientePerfilRepository.findById(atendimento);
    }

    public ResponseEntity<PacientePerfil> atualizarPacientePerfil(PacientePerfil pacientePerfil) {

        if (pacientePerfil.getAtendimento() == null) {
            throw new OperacaoNaoPermitidaException("O ID do atendimento não pode ser nulo.");
        }
        Optional<PacientePerfil> pacienteExistente = pacientePerfilRepository.findById(pacientePerfil.getAtendimento());

        if (pacienteExistente.isPresent()) {
            PacientePerfil paciente = pacienteExistente.get();

            paciente.setNome(pacientePerfil.getNome());
            paciente.setRg(pacientePerfil.getRg());

            PacientePerfil pacienteAtualizado = pacientePerfilRepository.save(paciente);
            return ResponseEntity.ok(pacienteAtualizado);
        } else {
            throw new OperacaoNaoPermitidaException("Paciente não encontrado");
        }
    }

    public void deletarPaciente(Long id) {
        Optional<PacientePerfil> paciente = pacientePerfilRepository.findById(id);

        if (paciente.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Paciente Não Encontrado!", null);
        }
        pacientePerfilRepository.delete(paciente.get());
    }
}

