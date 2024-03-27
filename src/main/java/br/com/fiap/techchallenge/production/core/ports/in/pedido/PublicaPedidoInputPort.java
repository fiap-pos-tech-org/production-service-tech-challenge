package br.com.fiap.techchallenge.production.core.ports.in.pedido;


import br.com.fiap.techchallenge.production.core.dtos.MensagemDTOBase;

public interface PublicaPedidoInputPort {
    void publicarFifo(MensagemDTOBase mensagem, String topicoArn);
}
