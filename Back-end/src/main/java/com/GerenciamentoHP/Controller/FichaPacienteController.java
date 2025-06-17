package com.GerenciamentoHP.Controller;

import com.GerenciamentoHP.Controller.Docs.FichaPacienteControllerDocs;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.GerenciamentoHP.Model.FichaPaciente;
import com.GerenciamentoHP.Services.FichaPacienteService;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Fichas", description = "Endpoints para mapeamento de Fichas")
public class FichaPacienteController implements FichaPacienteControllerDocs {

    @Autowired
    FichaPacienteService fichaPacienteService;


    @PostMapping("/criar-ficha")
    @Override
    public ResponseEntity<FichaPaciente> salvarFicha(@RequestBody FichaPaciente fichaPaciente) {
        FichaPaciente fichaSalva = fichaPacienteService.salvarFichaPaciente(fichaPaciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(fichaSalva);
    }

    @GetMapping("/buscar-fichas")
    @Override
    public List<FichaPaciente> findAll() {
        return fichaPacienteService.verTodasFichas();
    }


    @DeleteMapping("/deletar-ficha")
    @Override
    public ResponseEntity<Void> deletarFichaPaciente(@PathVariable Long id) {
        fichaPacienteService.deletarFicha(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("atualizar-ficha")
    @Override
    public ResponseEntity<FichaPaciente> atualizarFicha(@RequestBody FichaPaciente fichaPaciente) {
        return fichaPacienteService.atualizarFicha(fichaPaciente);
    }
}
