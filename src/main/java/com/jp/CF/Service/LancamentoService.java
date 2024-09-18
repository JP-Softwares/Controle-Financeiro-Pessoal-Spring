package com.jp.CF.Service;

import com.jp.CF.modelo.Lancamento;
import com.jp.CF.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public List<Lancamento> findAll() {
        return lancamentoRepository.findAll();
    }

    public Optional<Lancamento> findById(Long id) {
        return lancamentoRepository.findById(id);
    }

    public List<Lancamento> findByGrupo(Long grupoId) {
        return lancamentoRepository.findByGrupoId(grupoId);
    }

    public List<Lancamento> findByDateRange(LocalDate startDate, LocalDate endDate) {
        return lancamentoRepository.findByDataBetween(startDate, endDate);
    }

    public Lancamento save(Lancamento lancamento) {
        return lancamentoRepository.save(lancamento);
    }

    public void deleteById(Long id) {
        lancamentoRepository.deleteById(id);
    }
}
