package br.com.fiap.techchallenge.production.core.ports.out.pedido;

import br.com.fiap.techchallenge.production.core.domain.entities.enums.StatusPedidoEnum;
import br.com.fiap.techchallenge.production.core.dtos.PedidoDTO;

public interface AtualizaStatusPedidoOutputPort {
    PedidoDTO atualizarStatus(Long id, StatusPedidoEnum status);
}
