package br.com.jotavesecurity.ms_condominios.service;

import br.com.jotavesecurity.ms_condominios.entities.Apartamento;
import br.com.jotavesecurity.ms_condominios.repositories.ApartamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartamentoService {

    private ApartamentoRepository repository;

    public ApartamentoService(ApartamentoRepository repository){
        this.repository = repository;
    }


    public Apartamento create(Apartamento ap){
        return repository.save(ap);
    }

    public List<Apartamento> findAll(){
        return repository.findAll();
    }

    public Apartamento findById(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Apartamento não encontrado!");
        }
        return repository.findById(id).get();
    }

}
