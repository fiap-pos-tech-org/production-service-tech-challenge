package br.com.fiap.techchallenge.production.core.usecases.pedido;

import br.com.fiap.techchallenge.production.core.domain.entities.enums.StatusPedidoEnum;
import br.com.fiap.techchallenge.production.core.dtos.PedidoDTO;
import br.com.fiap.techchallenge.production.core.ports.in.pedido.AtualizaStatusPedidoInputPort;
import br.com.fiap.techchallenge.production.core.ports.out.pedido.AtualizaStatusPedidoOutputPort;

public class AtualizaStatusPedidoUseCase implements AtualizaStatusPedidoInputPort {
    private final AtualizaStatusPedidoOutputPort atualizaStatusPedidoOutputPort;

    public AtualizaStatusPedidoUseCase(AtualizaStatusPedidoOutputPort atualizaStatusPedidoOutputPort) {
        this.atualizaStatusPedidoOutputPort = atualizaStatusPedidoOutputPort;
    }

    @Override
    public PedidoDTO atualizarStatus(Long id, StatusPedidoEnum status) {
        return atualizaStatusPedidoOutputPort.atualizarStatus(id, status);
    }
}
