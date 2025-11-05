package br.com.jotavesecurity.ms_sensores.amqp;

import br.com.jotavesecurity.ms_sensores.dtos.AlertaDTO;

public interface AlertaProducer {

    public void publicarMensagemDeAlerta(AlertaDTO alertaDTO);
}
