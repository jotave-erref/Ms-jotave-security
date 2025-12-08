package br.com.jotavesecurity.msalertas.repository;

import br.com.jotavesecurity.msalertas.entities.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {
}
