package br.com.jotavesecurity.mscondominios.controller;

import br.com.jotavesecurity.mscondominios.dtos.ApartamentoDTO;
import br.com.jotavesecurity.mscondominios.mappers.ApartamentoMapper;
import br.com.jotavesecurity.mscondominios.service.ApartamentoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(apartamento.getId()).toUri();

        return ResponseEntity.created(uri).body(mapper.toDTO(apartamento));
    }

    @GetMapping
    public ResponseEntity<Page<ApartamentoDTO>> listarApartamentos(
            @PageableDefault(size = 10, sort = "numero") Pageable pageable){

        return ResponseEntity.ok(service.findAll(pageable).map(mapper::toDTO));

    }

    @GetMapping({"/{id}"})
    public ResponseEntity<ApartamentoDTO> buscarPorId(@PathVariable Long id){
        var apto = service.findById(id);

        var aptoDTO = mapper.toDTO(apto);

        return ResponseEntity.ok(aptoDTO);
    }
}
