package br.com.fiap.techchallenge.production.adapters.web.mappers;

import br.com.fiap.techchallenge.production.adapters.web.models.responses.ItemPedidoResponse;
import br.com.fiap.techchallenge.production.adapters.web.models.responses.PedidoResponse;
import br.com.fiap.techchallenge.production.core.dtos.ItemPedidoDTO;
import br.com.fiap.techchallenge.production.core.dtos.PedidoDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("PedidoMapperWeb")
public class PedidoMapper {

    public PedidoResponse toPedidoResponse(PedidoDTO pedido){
        return new PedidoResponse(
                pedido.id(),
                pedido.getNomeCliente(),
                toItemPedidoResponseList(pedido.itens()),
                pedido.status(),
                pedido.valorTotal(),
                pedido.dataCriacao()
        );
    }

    public List<PedidoResponse> toPedidoListResponse(List<PedidoDTO> pedidosOut) {
        List<PedidoResponse> pedidosResponse = new ArrayList<>();
        pedidosOut.forEach(pedidoOut -> pedidosResponse.add(toPedidoResponse(pedidoOut)));
        return pedidosResponse;
    }

    private List<ItemPedidoResponse> toItemPedidoResponseList(List<ItemPedidoDTO> itemPedidoOutList) {
        return itemPedidoOutList.stream().map(this::toItemPedidoResponse).toList();
    }

    private ItemPedidoResponse toItemPedidoResponse(ItemPedidoDTO itemPedidoOut) {
        return new ItemPedidoResponse(
                itemPedidoOut.produtoNome(),
                itemPedidoOut.produtoDescricao(),
                itemPedidoOut.valorUnitario(),
                itemPedidoOut.quantidade(),
                itemPedidoOut.getValorTotal()
        );
    }

}
