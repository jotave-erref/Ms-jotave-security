package br.com.jotavesecurity.ms_condominios.controller;

import br.com.jotavesecurity.ms_condominios.dtos.MoradorRequestDTO;
import br.com.jotavesecurity.ms_condominios.dtos.MoradorResponseDTO;
import br.com.jotavesecurity.ms_condominios.mappers.MoradorMapper;
import br.com.jotavesecurity.ms_condominios.service.MoradorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/moradores")
public class MoradorController {

    private final MoradorService service;

    private final MoradorMapper mapper;


    public MoradorController(MoradorService service, MoradorMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }


    @PostMapping
    public ResponseEntity<MoradorResponseDTO> criarMorador(@RequestBody @Valid MoradorRequestDTO dto){
        var moradorParaSalvar = mapper.toEntity(dto);
        var moradorSalvo = service.createMorador(moradorParaSalvar);
        var moradorResponseDTO = mapper.toDTO(moradorSalvo);

        return ResponseEntity.status(201).body(moradorResponseDTO);
    }
}
