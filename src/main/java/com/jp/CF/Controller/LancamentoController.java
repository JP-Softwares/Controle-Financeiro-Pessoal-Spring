package com.jp.CF.Controller;

import com.jp.CF.Service.LancamentoService;
import com.jp.CF.modelo.Lancamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoController {

    @Autowired
    private LancamentoService lancamentoService;

    @GetMapping
    public ResponseEntity<List<Lancamento>> getAllLancamentos() {
        List<Lancamento> lancamentos = lancamentoService.findAll();
        return ResponseEntity.ok(lancamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lancamento> getLancamentoById(@PathVariable Long id) {
        Optional<Lancamento> lancamento = lancamentoService.findById(id);
        if (lancamento.isPresent()) {
            return ResponseEntity.ok(lancamento.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/grupo/{grupoId}")
    public ResponseEntity<List<Lancamento>> getLancamentosByGrupo(@PathVariable Long grupoId) {
        List<Lancamento> lancamentos = lancamentoService.findByGrupo(grupoId);
        return ResponseEntity.ok(lancamentos);
    }

    @GetMapping("/datas")
    public ResponseEntity<List<Lancamento>> getLancamentosByDateRange(@RequestParam String startDate, @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<Lancamento> lancamentos = lancamentoService.findByDateRange(start, end);
        return ResponseEntity.ok(lancamentos);
    }

    @PostMapping
    public ResponseEntity<Lancamento> createLancamento(@RequestBody Lancamento lancamento) {
        Lancamento novoLancamento = lancamentoService.save(lancamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLancamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lancamento> updateLancamento(@PathVariable Long id, @RequestBody Lancamento lancamentoAtualizado) {
        Optional<Lancamento> lancamentoExistente = lancamentoService.findById(id);
        if (lancamentoExistente.isPresent()) {
            Lancamento lancamento = lancamentoExistente.get();
            lancamento.setValor(lancamentoAtualizado.getValor());
            lancamento.setTipo(lancamentoAtualizado.getTipo());
            lancamento.setCategoria(lancamentoAtualizado.getCategoria());
            lancamento.setData(lancamentoAtualizado.getData());
            Lancamento lancamentoSalvo = lancamentoService.save(lancamento);
            return ResponseEntity.ok(lancamentoSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLancamento(@PathVariable Long id) {
        Optional<Lancamento> lancamentoExistente = lancamentoService.findById(id);
        if (lancamentoExistente.isPresent()) {
            lancamentoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
