package br.com.fiap.techchallenge.production.adapters.repository.mappers;

import br.com.fiap.techchallenge.production.adapters.repository.models.ItemPedido;
import br.com.fiap.techchallenge.production.adapters.repository.models.Pedido;
import br.com.fiap.techchallenge.production.adapters.web.models.responses.PedidoResponse;
import br.com.fiap.techchallenge.production.core.dtos.PedidoDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoMapper {

    private final ItemPedidoMapper itemPedidoMapper;

    public PedidoMapper(ItemPedidoMapper itemPedidoMapper) {
        this.itemPedidoMapper = itemPedidoMapper;
    }

    public PedidoDTO responseToPedidoDTO(PedidoResponse pedidoResponse) {
        return new PedidoDTO(pedidoResponse);
    }

    public PedidoDTO toPedidoDTO(Pedido pedido) {
        var listaItemPedidoOut = itemPedidoMapper.toItemPedidoResponse(pedido.getItens());

        return new PedidoDTO(pedido.getId(), listaItemPedidoOut, pedido.getStatus(), pedido.getData());
    }

    public List<PedidoDTO> toListaPedidoDTO(List<PedidoResponse> pedidoResponse) {
        return pedidoResponse.stream().map(this::responseToPedidoDTO).toList();
    }

    public Pedido toPedido(PedidoDTO pedidoIn) {
        var pedido = new Pedido(
                pedidoIn.id(),
                pedidoIn.status(),
                pedidoIn.dataCriacao()
        );

        List<ItemPedido> itemsPedido = pedidoIn.itens().stream().map(item -> new ItemPedido(
                pedido,
                item.produtoNome(),
                item.quantidade()
        )).toList();

        pedido.setItens(itemsPedido);
        return pedido;
    }
}
