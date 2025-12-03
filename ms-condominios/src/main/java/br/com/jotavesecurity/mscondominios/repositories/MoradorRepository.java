package br.com.jotavesecurity.mscondominios.repositories;

import br.com.jotavesecurity.mscondominios.entities.Morador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoradorRepository extends JpaRepository<Morador, Long> {
}
