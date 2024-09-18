package com.jp.CF.Service;

import com.jp.CF.modelo.Grupo;
import com.jp.CF.modelo.Pessoa;
import com.jp.CF.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    public List<Grupo> findAll() {
        return grupoRepository.findAll();
    }

    public List<Grupo> findByPessoa(Pessoa pessoa) {
        return grupoRepository.findByPessoaId(pessoa.getId());
    }

    public Optional<Grupo> findById(Long id) {
        return grupoRepository.findById(id);
    }

    public Grupo save(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    public void deleteById(Long id) {
        grupoRepository.deleteById(id);
    }
}
