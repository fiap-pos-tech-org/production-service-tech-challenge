package br.com.fiap.techchallenge.production.core.ports.in.pedido;


import br.com.fiap.techchallenge.production.core.dtos.PedidoDTO;
import br.com.fiap.techchallenge.production.core.domain.entities.enums.StatusPedidoEnum;

import java.util.List;

public interface BuscaTodosPedidosPorStatusInputPort {
    List<PedidoDTO> buscarTodosStatus(StatusPedidoEnum status);
}
