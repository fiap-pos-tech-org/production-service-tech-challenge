package br.com.fiap.techchallenge.production.core.usecases.pedido;

import br.com.fiap.techchallenge.production.core.dtos.PedidoDTO;
import br.com.fiap.techchallenge.production.core.ports.in.pedido.BuscaTodosPedidosInputPort;
import br.com.fiap.techchallenge.production.core.ports.out.pedido.BuscaTodosPedidosOutputPort;

import java.util.List;

public class BuscaTodosPedidosUseCase implements BuscaTodosPedidosInputPort {

    private final BuscaTodosPedidosOutputPort buscaTodosPedidosOutputPort;

    public BuscaTodosPedidosUseCase(BuscaTodosPedidosOutputPort buscaTodosPedidosOutputPort) {
        this.buscaTodosPedidosOutputPort = buscaTodosPedidosOutputPort;
    }

    @Override
    public List<PedidoDTO> buscarTodos() {
        return buscaTodosPedidosOutputPort.buscarTodos();
    }
}
