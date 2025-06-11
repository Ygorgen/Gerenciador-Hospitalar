package com.GerenciamentoHP.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.GerenciamentoHP.Model.FichaPaciente;
import com.GerenciamentoHP.Services.FichaPacienteService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FichaPacienteController {
    
    @Autowired
    FichaPacienteService fichaPacienteService;


    @PostMapping("/criar-ficha")
    public ResponseEntity<FichaPaciente> salvarFicha(@RequestBody FichaPaciente fichaPaciente){
        FichaPaciente fichaSalva = fichaPacienteService.salvarFichaPaciente(fichaPaciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(fichaSalva);
    }

    @GetMapping("/buscar-fichas")
    public List <FichaPaciente> findAll(){
        return fichaPacienteService.verTodasFichas();
    }



    @DeleteMapping("/deletar-ficha")
    public ResponseEntity<Void> deletarFichaPaciente(@PathVariable Long id){
        fichaPacienteService.deletarFicha(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("atualizar-ficha")
    public ResponseEntity<FichaPaciente> atualizarFicha(@RequestBody FichaPaciente fichaPaciente) {
        return fichaPacienteService.atualizarFicha(fichaPaciente);
    }
}
