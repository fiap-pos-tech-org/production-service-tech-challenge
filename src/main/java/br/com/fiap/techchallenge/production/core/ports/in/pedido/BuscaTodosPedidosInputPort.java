package br.com.fiap.techchallenge.production.core.ports.in.pedido;


import br.com.fiap.techchallenge.production.core.dtos.PedidoDTO;

import java.util.List;

public interface BuscaTodosPedidosInputPort {
    List<PedidoDTO> buscarTodos();
}
