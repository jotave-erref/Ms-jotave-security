package br.com.jotavesecurity.ms_sensores.amqp;

import br.com.jotavesecurity.ms_sensores.dtos.AlertaDTO;

public interface AlertaProducer {

    void publicarMensagemDeAlerta(AlertaDTO alertaDTO);
}
