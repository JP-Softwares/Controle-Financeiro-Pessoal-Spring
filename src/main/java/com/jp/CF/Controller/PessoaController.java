package com.jp.CF.Controller;
import com.jp.CF.Service.PessoaService;
import com.jp.CF.modelo.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<List<Pessoa>> getAllPessoas() {
        List<Pessoa> pessoas = pessoaService.findAll();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable Long id) {
        Optional<Pessoa> pessoa = pessoaService.findById(id);
        if (pessoa.isPresent()) {
            return ResponseEntity.ok(pessoa.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<Pessoa> createPessoa(@RequestBody Pessoa pessoa) {
        Pessoa novaPessoa = pessoaService.save(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPessoa);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id, @RequestBody Pessoa pessoaAtualizada) {
        Optional<Pessoa> pessoaExistente = pessoaService.findById(id);
        if (pessoaExistente.isPresent()) {
            Pessoa pessoa = pessoaExistente.get();
            pessoa.setNome(pessoaAtualizada.getNome());
            Pessoa pessoaSalva = pessoaService.save(pessoa);
            return ResponseEntity.ok(pessoaSalva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
        Optional<Pessoa> pessoaExistente = pessoaService.findById(id);
        if (pessoaExistente.isPresent()) {
            pessoaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}