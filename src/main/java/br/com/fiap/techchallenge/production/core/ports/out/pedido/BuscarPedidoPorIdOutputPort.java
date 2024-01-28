package br.com.fiap.techchallenge.production.core.ports.out.pedido;

import br.com.fiap.techchallenge.production.core.dtos.PedidoDTO;

public interface BuscarPedidoPorIdOutputPort {

    PedidoDTO buscarPorId(Long id);
}
