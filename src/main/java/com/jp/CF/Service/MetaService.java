package com.jp.CF.Service;

import com.jp.CF.modelo.Meta;
import com.jp.CF.repository.MetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetaService {

    @Autowired
    private MetaRepository metaRepository;

    public List<Meta> findAll() {
        return metaRepository.findAll();
    }

    public Optional<Meta> findById(Long id) {
        return metaRepository.findById(id);
    }

    public List<Meta> findByPessoa(Long pessoaId) {
        return metaRepository.findByPessoaId(pessoaId);
    }

    public List<Meta> findByGrupo(Long grupoId) {
        return metaRepository.findByGrupoId(grupoId);
    }

    public Meta save(Meta meta) {
        return metaRepository.save(meta);
    }

    public void deleteById(Long id) {
        metaRepository.deleteById(id);
    }
}
