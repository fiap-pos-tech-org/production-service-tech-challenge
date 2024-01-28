package br.com.fiap.techchallenge.production.core.ports.out.pedido;

import br.com.fiap.techchallenge.production.core.dtos.PedidoDTO;

import java.util.List;

public interface BuscaTodosPedidosOutputPort {
    List<PedidoDTO> buscarTodos();
}
