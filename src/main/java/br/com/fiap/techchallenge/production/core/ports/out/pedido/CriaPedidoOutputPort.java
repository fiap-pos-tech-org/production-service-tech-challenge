package br.com.fiap.techchallenge.production.core.ports.out.pedido;

import br.com.fiap.techchallenge.production.core.dtos.PedidoDTO;

public interface CriaPedidoOutputPort {
    PedidoDTO criar(PedidoDTO criaPedidoIn);
}
