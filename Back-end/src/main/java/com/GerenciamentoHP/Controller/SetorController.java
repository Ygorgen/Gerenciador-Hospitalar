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
import com.GerenciamentoHP.Model.Setor;
import com.GerenciamentoHP.Service.SetorService;

@RestController
@RequestMapping("/setores")
public class SetorController {

    @Autowired
    private SetorService setorService;

    @PostMapping
    public ResponseEntity<Setor> registrarSetor(@RequestBody Setor setor) {
        Setor setorCadastro = setorService.salvarSetor(setor);
        return ResponseEntity.status(HttpStatus.CREATED).body(setorCadastro);
    }

    @GetMapping
    public List<Setor> listarSetores() {
        return setorService.listarTodosSetores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Setor> buscarSetorPorId(@PathVariable Long id) {
        return setorService.buscarSetorPorId(id);
    }

    @PutMapping()
    public ResponseEntity<Setor> atualizarSetor(@RequestBody Setor setor) {
        return setorService.atualizarSetor(setor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSetor(@PathVariable Long id) {
        setorService.deletarSetor(id);
        return ResponseEntity.ok().body(null);
    }

}
