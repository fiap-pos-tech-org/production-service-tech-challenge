package br.com.fiap.techchallenge.production.core.ports.out.pedido;


import br.com.fiap.techchallenge.production.core.dtos.MensagemDTOBase;

public interface PublicaPedidoOutputPort {
    void publicarFifo(MensagemDTOBase mensagem, String topicoArn);
}
