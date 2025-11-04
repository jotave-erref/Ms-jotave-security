package br.com.jotavesecurity.ms_condominios.controller;

import br.com.jotavesecurity.ms_condominios.dtos.ApartamentoDTO;
import br.com.jotavesecurity.ms_condominios.mappers.ApartamentoMapper;
import br.com.jotavesecurity.ms_condominios.service.ApartamentoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apartamentos")
public class ApartamentoController {

    private final ApartamentoService service;

    private final ApartamentoMapper mapper;


    public ApartamentoController(ApartamentoService service, ApartamentoMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }


    @PostMapping
    public ResponseEntity<ApartamentoDTO> criarApartamento(@RequestBody @Valid ApartamentoDTO dto){
        var apartamento = mapper.toEntity(dto);
        service.create(apartamento);
        var reponseDTO = mapper.toDTO(apartamento);

        return ResponseEntity.status(201).body(reponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ApartamentoDTO>> listarApartamentos(){
        var listaDeApartamentos = service.findAll();

        var reponseDTO = mapper.toDTOList(listaDeApartamentos);

        return ResponseEntity.ok(reponseDTO);

    }

    @GetMapping({"/{id}"})
    public ResponseEntity<ApartamentoDTO> buscarPorId(@PathVariable Long id){
        var apto = service.findById(id);

        var aptoDTO = mapper.toDTO(apto);

        return ResponseEntity.ok(aptoDTO);
    }
}
