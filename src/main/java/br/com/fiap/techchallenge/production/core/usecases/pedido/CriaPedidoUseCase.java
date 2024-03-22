package br.com.fiap.techchallenge.production.core.usecases.pedido;

import br.com.fiap.techchallenge.production.core.dtos.CriaPedidoDTO;
import br.com.fiap.techchallenge.production.core.dtos.PedidoDTO;
import br.com.fiap.techchallenge.production.core.ports.in.pedido.CriaPedidoInputPort;
import br.com.fiap.techchallenge.production.core.ports.out.pedido.CriaPedidoOutputPort;

import java.time.LocalDateTime;

public class CriaPedidoUseCase implements CriaPedidoInputPort {


    private final CriaPedidoOutputPort criaPedidoOutputPort;

    public CriaPedidoUseCase(CriaPedidoOutputPort criaPedidoOutputPort) {
        this.criaPedidoOutputPort = criaPedidoOutputPort;
    }

    @Override
    public PedidoDTO criar(CriaPedidoDTO pedidoIn) {
        return criaPedidoOutputPort.criar(new PedidoDTO(
                pedidoIn.idPedido(),
                pedidoIn.itens(),
                pedidoIn.status(),
                LocalDateTime.now()
        ));
    }
}
