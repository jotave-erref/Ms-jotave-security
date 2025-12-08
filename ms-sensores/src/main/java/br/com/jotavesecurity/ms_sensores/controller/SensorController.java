package br.com.jotavesecurity.ms_sensores.controller;

import br.com.jotavesecurity.ms_sensores.dtos.SensorEventDTO;
import br.com.jotavesecurity.ms_sensores.service.SensorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventos")
@RequiredArgsConstructor
public class SensorController {

    private final SensorService service;


    @PostMapping
    public ResponseEntity<Void> receberEvento(@RequestBody @Valid SensorEventDTO dto){
        service.processarEvento(dto);

        return ResponseEntity.accepted().build();
    }
}
