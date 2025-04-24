package com.GerenciamentoHP.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.GerenciamentoHP.DTO.PacientePerfilDto;
import com.GerenciamentoHP.Model.PacientePerfil;
import com.GerenciamentoHP.Service.PacientePerfilService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pacientes-perfil")
public class PacientePerfilController {

    @Autowired
    private PacientePerfilService pacientePerfilService;

    @PostMapping("/cadastro")
    public ResponseEntity<PacientePerfil> cadastrarPaciente(@RequestBody PacientePerfilDto pacienteDto) {
        PacientePerfil pacientePerfil = pacientePerfilService.salvarPerfil(pacienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacientePerfil);
    }

    @GetMapping
    public List<PacientePerfil> findAll() {
        return pacientePerfilService.verTodosPacientes();
    }

    @GetMapping("{rg}")
    public ResponseEntity<PacientePerfil> buscarPacientePorRg(@PathVariable Integer rg) {
        return pacientePerfilService.buscarPorRg(rg);
    }

    @PutMapping("/{atendimento}")
    public ResponseEntity<PacientePerfil> atualizarPacientePerfil(@PathVariable Long atendimento,
            @Valid @RequestBody PacientePerfil pacientePerfil) {
        if (atendimento == null) {
            return ResponseEntity.badRequest().body(null);
        }

        pacientePerfil.setAtendimento(atendimento);

        return pacientePerfilService.atualizarPacientePerfil(pacientePerfil);
    }

    @DeleteMapping("/{atendimento}")
    public ResponseEntity<Void> deletarPerfilPaciente(@PathVariable Long atendimento) {
        pacientePerfilService.deletarPaciente(atendimento);
        return ResponseEntity.ok().build();
    }
}
