package br.com.jotavesecurity.ms_condominios.repositories;

import br.com.jotavesecurity.ms_condominios.entities.Morador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoradorRepository extends JpaRepository<Morador, Long> {
}
