package br.com.fiap.techchallenge.production.core.usecases.pedido;


import br.com.fiap.techchallenge.production.core.dtos.MensagemDTOBase;
import br.com.fiap.techchallenge.production.core.ports.in.pedido.PublicaPedidoInputPort;
import br.com.fiap.techchallenge.production.core.ports.out.pedido.PublicaPedidoOutputPort;

public class PublicaPedidoUseCase implements PublicaPedidoInputPort {

    PublicaPedidoOutputPort publicaPedido;

    public PublicaPedidoUseCase(PublicaPedidoOutputPort publicaPedido) {
        this.publicaPedido = publicaPedido;
    }

    @Override
    public void publicarFifo(MensagemDTOBase mensagem, String topicoArn) {
        publicaPedido.publicarFifo(mensagem, topicoArn);
    }
}
