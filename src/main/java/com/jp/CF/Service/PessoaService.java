package com.jp.CF.Service;

import com.jp.CF.modelo.Pessoa;
import com.jp.CF.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;


    private boolean verificarNomeUnico(String nome) {
        Optional<Pessoa> pessoaComMesmoNome = pessoaRepository.findByNome(nome);
        if (pessoaComMesmoNome.isPresent()) {
            return false;
        }
        return true;
    }

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> findById(Long id) {
        return pessoaRepository.findById(id);
    }

    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public void deleteById(Long id) {
        pessoaRepository.deleteById(id);
    }
}
