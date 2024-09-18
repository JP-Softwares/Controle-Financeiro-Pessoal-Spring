package com.jp.CF.repository;


import com.jp.CF.modelo.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

    List<Lancamento> findByGrupoId(Long grupoId);

    List<Lancamento> findByDataBetween(LocalDate startDate, LocalDate endDate);

    List<Lancamento> findByGrupoIdAndTipo(Long grupoId, String tipo);
}
