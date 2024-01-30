package br.com.fiap.techchallenge.production.adapters.repository.mappers;

import br.com.fiap.techchallenge.production.adapters.web.models.responses.PedidoResponse;
import br.com.fiap.techchallenge.production.core.dtos.PedidoDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoMapper {

    public PedidoDTO toPedidoDTO(PedidoResponse pedidoResponse) {
        return new PedidoDTO(pedidoResponse);
    }

    public List<PedidoDTO> toListaPedidoDTO(List<PedidoResponse> pedidoResponse) {
        return pedidoResponse.stream().map(this::toPedidoDTO).toList();
    }
}
