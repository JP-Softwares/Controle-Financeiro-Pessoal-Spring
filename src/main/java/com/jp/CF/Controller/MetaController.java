package com.jp.CF.Controller;

import com.jp.CF.Service.GrupoService;
import com.jp.CF.Service.MetaService;
import com.jp.CF.Service.PessoaService;
import com.jp.CF.modelo.Grupo;
import com.jp.CF.modelo.Meta;
import com.jp.CF.modelo.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/metas")
public class MetaController {

    @Autowired
    private MetaService metaService;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private GrupoService grupoService;

    @GetMapping
    public ResponseEntity<List<Meta>> getAllMetas() {
        List<Meta> metas = metaService.findAll();
        return ResponseEntity.ok(metas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meta> getMetaById(@PathVariable Long id) {
        Optional<Meta> meta = metaService.findById(id);
        if (meta.isPresent()) {
            return ResponseEntity.ok(meta.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<List<Meta>> getMetasByPessoa(@PathVariable Long pessoaId) {
        Optional<Pessoa> pessoa = pessoaService.findById(pessoaId);
        if (pessoa.isPresent()) {
            List<Meta> metas = metaService.findByPessoa(pessoaId);
            return ResponseEntity.ok(metas);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/grupo/{grupoId}")
    public ResponseEntity<List<Meta>> getMetasByGrupo(@PathVariable Long grupoId) {
        Optional<Grupo> grupo = grupoService.findById(grupoId);
        if (grupo.isPresent()) {
            List<Meta> metas = metaService.findByGrupo(grupoId);
            return ResponseEntity.ok(metas);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Meta> createMeta(@RequestBody Meta meta) {
        Optional<Grupo> grupo = grupoService.findById(meta.getGrupo().getId());
        if (grupo.isPresent()) {
            Meta novaMeta = metaService.save(meta);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaMeta);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Grupo não encontrado
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Meta> updateMeta(@PathVariable Long id, @RequestBody Meta metaAtualizada) {
        Optional<Meta> metaExistente = metaService.findById(id);
        if (metaExistente.isPresent()) {
            Meta meta = metaExistente.get();

            Meta metaSalva = metaService.save(meta);
            return ResponseEntity.ok(metaSalva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeta(@PathVariable Long id) {
        Optional<Meta> metaExistente = metaService.findById(id);
        if (metaExistente.isPresent()) {
            metaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
