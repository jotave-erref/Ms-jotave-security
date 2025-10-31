package br.com.jotavesecurity.ms_condominios.service;

import br.com.jotavesecurity.ms_condominios.entities.Morador;
import br.com.jotavesecurity.ms_condominios.repositories.ApartamentoRepository;
import br.com.jotavesecurity.ms_condominios.repositories.MoradorRepository;
import org.springframework.stereotype.Service;

@Service
public class MoradorService {

    private MoradorRepository repository;

    private ApartamentoRepository apRepository;


    public MoradorService(MoradorRepository repository, ApartamentoRepository apRepository){
        this.repository = repository;
        this.apRepository = apRepository;
    }


    // REGRA DE NEGÓCIO: Não podemos cadastrar um morador em um apartamento que não existe.
    public Morador createMorador(Morador morador){
        long apartamentoId = morador.getApartamento().getId();
        var apartamento = apRepository.findById(apartamentoId).
                orElseThrow(() -> new RuntimeException("Apartamento não encontrado com o id" + apartamentoId));

        // Se o apartamento foi encontrado, associamos a entidade completa ao morador antes de salvar.
        morador.setApartamento(apartamento);
        return repository.save(morador);
    }
}
