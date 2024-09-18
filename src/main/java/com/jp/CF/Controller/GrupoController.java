package com.jp.CF.Controller;

import com.jp.CF.Service.GrupoService;
import com.jp.CF.Service.PessoaService;
import com.jp.CF.modelo.Grupo;
import com.jp.CF.modelo.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/grupos")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<List<Grupo>> getAllGrupos() {
        List<Grupo> grupos = grupoService.findAll();
        return ResponseEntity.ok(grupos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grupo> getGrupoById(@PathVariable Long id) {
        Optional<Grupo> grupo = grupoService.findById(id);
        if (grupo.isPresent()) {
            return ResponseEntity.ok(grupo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<List<Grupo>> getGruposByPessoa(@PathVariable Long pessoaId) {
        Optional<Pessoa> pessoa = pessoaService.findById(pessoaId);
        if (pessoa.isPresent()) {
            List<Grupo> grupos = grupoService.findByPessoa(pessoa.get());
            return ResponseEntity.ok(grupos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Grupo> createGrupo(@RequestBody Grupo grupo) {
        Grupo novoGrupo = grupoService.save(grupo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoGrupo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grupo> updateGrupo(@PathVariable Long id, @RequestBody Grupo grupoAtualizado) {
        Optional<Grupo> grupoExistente = grupoService.findById(id);
        if (grupoExistente.isPresent()) {
            Grupo grupo = grupoExistente.get();
            grupo.setNome(grupoAtualizado.getNome());
            grupo.setSaldo(grupoAtualizado.getSaldo());
            Grupo grupoSalvo = grupoService.save(grupo);
            return ResponseEntity.ok(grupoSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrupo(@PathVariable Long id) {
        Optional<Grupo> grupoExistente = grupoService.findById(id);
        if (grupoExistente.isPresent()) {
            grupoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
