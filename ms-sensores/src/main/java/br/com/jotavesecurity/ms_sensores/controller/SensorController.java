package br.com.jotavesecurity.ms_sensores.controller;

import br.com.jotavesecurity.ms_sensores.dtos.SensorEventDTO;
import br.com.jotavesecurity.ms_sensores.service.SensorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventos")
public class SensorController {

    private final SensorService service;


    public SensorController(SensorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> receberEvento(@RequestBody SensorEventDTO dto){
        service.processarEvento(dto);

        // Retornamos 202 Accepted.
        // Isso significa "Recebi seu pedido e vou processá-lo"
        return ResponseEntity.accepted().build();
    }
}
