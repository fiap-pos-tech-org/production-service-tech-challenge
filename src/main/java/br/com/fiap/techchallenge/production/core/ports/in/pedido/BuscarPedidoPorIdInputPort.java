package br.com.fiap.techchallenge.production.core.ports.in.pedido;

import br.com.fiap.techchallenge.production.core.dtos.PedidoDTO;

public interface BuscarPedidoPorIdInputPort {
    PedidoDTO buscarPorId(Long id);
}
