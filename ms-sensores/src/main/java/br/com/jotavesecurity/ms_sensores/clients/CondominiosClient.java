package br.com.jotavesecurity.ms_sensores.clients;

import br.com.jotavesecurity.ms_sensores.dtos.ApartamentoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-condominios", fallback = CondominiosClientFallback.class)
public interface CondominiosClient {

    @GetMapping("/apartamentos/{id}")
    ApartamentoResponseDTO buscarPorId(@PathVariable("id") Long id);

}
