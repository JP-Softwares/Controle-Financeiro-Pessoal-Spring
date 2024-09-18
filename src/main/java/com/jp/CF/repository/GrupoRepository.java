package com.jp.CF.repository;

import com.jp.CF.modelo.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

    List<Grupo> findByPessoaId(Long pessoaId);
}
