package br.com.jotavesecurity.mscondominios.repositories;

import br.com.jotavesecurity.mscondominios.entities.Apartamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartamentoRepository extends JpaRepository<Apartamento, Long> {
}
