package br.com.jotavesecurity.ms_condominios.repositories;

import br.com.jotavesecurity.ms_condominios.entities.Apartamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartamentoRepository extends JpaRepository<Apartamento, Long> {
}
