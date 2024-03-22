package br.com.fiap.techchallenge.production.adapters.repository.mappers;

import br.com.fiap.techchallenge.production.adapters.repository.models.ItemPedido;
import br.com.fiap.techchallenge.production.core.dtos.ItemPedidoDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemPedidoMapper {

    public List<ItemPedidoDTO> toItemPedidoResponse(List<ItemPedido> itens) {
        var listaItemPedidoOut = new ArrayList<ItemPedidoDTO>();

        itens.forEach(item -> {
            var itemPedidoOut = new ItemPedidoDTO(
                    item.getProdutoNome(),
                    item.getQuantidade()
            );
            listaItemPedidoOut.add(itemPedidoOut);
        });

        return listaItemPedidoOut;
    }
}
