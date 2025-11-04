package br.com.jotavesecurity.ms_sensores.service;

import br.com.jotavesecurity.ms_sensores.clients.CondominiosClient;
import br.com.jotavesecurity.ms_sensores.dtos.ApartamentoResponseDTO;
import br.com.jotavesecurity.ms_sensores.dtos.SensorEventDTO;
import org.springframework.stereotype.Service;

@Service
public class SensorService {

    private final CondominiosClient client;

    public SensorService(CondominiosClient client){
        this.client = client;
    }

    public void processarEvento(SensorEventDTO dto){
        System.out.println("Recebido o evento do sensor: " + dto);

        // 1. Extrai o ID do apartamento a partir do sensorId.
        String numeroApto = dto.sensorId().replaceAll("\\D+", "");
        Long aptoId = Long.parseLong(numeroApto);

        // 2. Usa o Feign Client para buscar os dados do apartamento no ms-condominos.
        System.out.println("Buscando dados do apartamento com ID: " + aptoId);
        ApartamentoResponseDTO apto = client.buscarPorId(aptoId);
        System.out.println("Dados recebidos do ms-condominios: " + apto);

        // Regra de negócio caso o alarme for acionado entre as 00 hrs e 6hrs
        boolean isAlerta = "ABERTO".equalsIgnoreCase(dto.status()) &&
                (dto.timestamp().getHour() >= 0 && dto.timestamp().getHour() <= 6);

        if(isAlerta){
            System.out.println("ALERTA GERADO!!! " +
                    "MOVIMENTO SUSPEITO NO APARTAMENTO " + apto.numero() + ", BLOCO " + apto.bloco());
        }


    }
}
