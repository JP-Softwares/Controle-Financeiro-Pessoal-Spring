package com.jp.CF.repository;

import com.jp.CF.modelo.Meta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetaRepository extends JpaRepository<Meta, Long> {

    // Consulta para buscar metas por grupo
    List<Meta> findByGrupoId(Long grupoId);
}
