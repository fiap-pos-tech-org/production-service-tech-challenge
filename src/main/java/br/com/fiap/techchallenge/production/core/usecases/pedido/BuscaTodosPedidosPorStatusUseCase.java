package br.com.fiap.techchallenge.production.core.usecases.pedido;

import br.com.fiap.techchallenge.production.core.domain.entities.enums.StatusPedidoEnum;
import br.com.fiap.techchallenge.production.core.dtos.PedidoDTO;
import br.com.fiap.techchallenge.production.core.ports.in.pedido.BuscaTodosPedidosPorStatusInputPort;
import br.com.fiap.techchallenge.production.core.ports.out.pedido.BuscaTodosPedidosPorStatusOutputPort;

import java.util.List;

public class BuscaTodosPedidosPorStatusUseCase implements BuscaTodosPedidosPorStatusInputPort {

    private final BuscaTodosPedidosPorStatusOutputPort buscaTodosPedidosPorStatusOutputPort;

    public BuscaTodosPedidosPorStatusUseCase(BuscaTodosPedidosPorStatusOutputPort buscaTodosPedidosPorStatusOutputPort) {
        this.buscaTodosPedidosPorStatusOutputPort = buscaTodosPedidosPorStatusOutputPort;
    }

    @Override
    public List<PedidoDTO> buscarTodosStatus(StatusPedidoEnum status) {
        return buscaTodosPedidosPorStatusOutputPort.buscarPedidosPorStatus(status);
    }

}
