package com.GerenciamentoHP.Controller;

import com.GerenciamentoHP.Controller.DTO.ResultadoPesquisaPacienteDTO;
import com.GerenciamentoHP.Model.PacientePerfil;
import com.GerenciamentoHP.Repository.PacientePerfilRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.GerenciamentoHP.Model.FichaPaciente;
import com.GerenciamentoHP.Service.FichaPacienteService;

import java.util.List;

@RestController
@RequestMapping("/ficha-paciente")
public class FichaPacienteController {
    
    @Autowired
    FichaPacienteService fichaPacienteService;

    @PostMapping
    public ResponseEntity<FichaPaciente> salvarFicha(@RequestBody FichaPaciente fichaPaciente){
        FichaPaciente fichaSalva = fichaPacienteService.salvarFichaPaciente(fichaPaciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(fichaSalva);
    }

    @GetMapping
    public List <FichaPaciente> findAll(){
        return fichaPacienteService.verTodasFichas();
    }



    @DeleteMapping
    public ResponseEntity<Void> deletarFichaPaciente(@PathVariable Long id){
        fichaPacienteService.deletarFicha(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<FichaPaciente> atualizarFicha(@RequestBody FichaPaciente fichaPaciente) {
        return fichaPacienteService.atualizarFicha(fichaPaciente);
    }
}
