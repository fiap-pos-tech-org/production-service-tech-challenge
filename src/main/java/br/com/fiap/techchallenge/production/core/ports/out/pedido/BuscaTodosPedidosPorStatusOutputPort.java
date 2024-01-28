package br.com.fiap.techchallenge.production.core.ports.out.pedido;

import br.com.fiap.techchallenge.production.core.dtos.PedidoDTO;
import br.com.fiap.techchallenge.production.core.domain.entities.enums.StatusPedidoEnum;

import java.util.List;

public interface BuscaTodosPedidosPorStatusOutputPort {
    List<PedidoDTO> buscarPedidosPorStatus(StatusPedidoEnum status);
}
