package br.com.fiap.techchallenge.production.core.ports.in.pedido;

import br.com.fiap.techchallenge.production.core.dtos.CriaPedidoDTO;
import br.com.fiap.techchallenge.production.core.dtos.PedidoDTO;

public interface CriaPedidoInputPort {
    PedidoDTO criar(CriaPedidoDTO pedidoIn);
}
