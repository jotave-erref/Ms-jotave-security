package br.com.jotavesecurity.mscondominios.service;

import br.com.jotavesecurity.mscondominios.entities.Apartamento;
import br.com.jotavesecurity.mscondominios.infra.exceptions.RecursoNaoEncontradoException;
import br.com.jotavesecurity.mscondominios.repositories.ApartamentoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ApartamentoService {

    private ApartamentoRepository repository;

    public ApartamentoService(ApartamentoRepository repository){
        this.repository = repository;
    }


    public Apartamento create(Apartamento ap){
        return repository.save(ap);
    }

    public Page<Apartamento> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Apartamento findById(Long id){

        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Apartamento não encontrado com o ID: " + id));
    }

}
