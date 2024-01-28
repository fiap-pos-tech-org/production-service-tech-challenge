package br.com.fiap.techchallenge.production.core.ports.in.pedido;

import br.com.fiap.techchallenge.production.core.dtos.AtualizaStatusPedidoDTO;
import br.com.fiap.techchallenge.production.core.dtos.PedidoDTO;

public interface AtualizaStatusPedidoInputPort {
    PedidoDTO atualizarStatus(Long id, AtualizaStatusPedidoDTO pedidoStatusIn);
}
