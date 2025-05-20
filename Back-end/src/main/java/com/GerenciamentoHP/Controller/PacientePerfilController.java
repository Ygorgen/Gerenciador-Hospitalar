package com.GerenciamentoHP.Controller;

import java.util.List;

import com.GerenciamentoHP.Controller.mappers.PacienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<PacientePerfilDto>> pesquisa(
            @RequestParam(value = "nome", required = false)
            String nome,

            @RequestParam(value = "pagina",defaultValue = "0")
            Integer pagina,

            @RequestParam(value = "tamanho-pagina",defaultValue = "10")
            Integer tamanhoPagina,

            @RequestParam(value = "atendimento", required = false)
            Long atendimento
    ){

        Page<PacientePerfil> paginaResultado = pacientePerfilService.pesquisa(nome, atendimento,pagina,tamanhoPagina);

        Page<PacientePerfilDto> resultado = paginaResultado.map(mapper::toDTO);

        return ResponseEntity.ok(resultado);
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
