package com.GerenciamentoHP.Controller;

import java.util.List;
import java.util.stream.Collectors;

import com.GerenciamentoHP.Controller.DTO.ResultadoPesquisaPacienteDTO;
import com.GerenciamentoHP.Controller.mappers.PacienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.GerenciamentoHP.Controller.DTO.PacientePerfilDto;
import com.GerenciamentoHP.Model.PacientePerfil;
import com.GerenciamentoHP.Service.PacientePerfilService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pacientes-perfil")
public class PacientePerfilController {

    @Autowired
    private PacientePerfilService pacientePerfilService;

    @Autowired
    private PacienteMapper mapper;

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrarPaciente(@RequestBody @Valid PacientePerfilDto pacienteDto) {
        return pacientePerfilService.salvarPerfil(pacienteDto);
    }

    @GetMapping("/pacientes")
    public List<PacientePerfil> findAll() {
        return pacientePerfilService.verTodosPacientes();
    }

    @GetMapping
    public ResponseEntity<List<ResultadoPesquisaPacienteDTO>> pesquisa(
            @RequestParam(value = "nome", required = false)
            String nome,

            @RequestParam(value = "atendimento", required = false)
            Long atendimento) {

        var resultado = pacientePerfilService.pesquisa(nome, atendimento);
        var lista =
                resultado.stream().map(mapper::toEntity).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @GetMapping("{rg}")
    public ResponseEntity<PacientePerfil> buscarPacientePorRg(@PathVariable String rg) {
        return pacientePerfilService.buscarPorRg(rg);
    }

    @PutMapping("/atualizar-perfil/{atendimento}")
    public ResponseEntity<?> atualizarPacientePerfil(@PathVariable Long atendimento, @Valid @RequestBody PacientePerfil pacientePerfil) {

        pacientePerfil.setAtendimento(atendimento);
        return pacientePerfilService.atualizarPacientePerfil(pacientePerfil);
    }

    @DeleteMapping("/{atendimento}")
    public ResponseEntity<Void> deletarPerfilPaciente(@PathVariable Long atendimento) {
        pacientePerfilService.deletarPaciente(atendimento);
        return ResponseEntity.ok().build();
    }
}
