package br.com.fiap.techchallenge.production.utils;

import br.com.fiap.techchallenge.production.adapters.repository.models.Produto;
import br.com.fiap.techchallenge.production.adapters.web.models.requests.ItemPedidoRequest;
import br.com.fiap.techchallenge.production.adapters.web.models.responses.ItemPedidoResponse;
import br.com.fiap.techchallenge.production.core.dtos.CriaItemPedidoDTO;
import br.com.fiap.techchallenge.production.core.dtos.ItemPedidoDTO;

import java.math.BigDecimal;
import java.util.List;

public class ItemPedidoHelper {

    public static ItemPedidoDTO criaItemPedidoDTO() {
        Produto produto = ProdutoHelper.criaProduto();
        return new ItemPedidoDTO(produto.getId(), produto.getNome(), produto.getDescricao(), BigDecimal.valueOf(1L), 1);
    }

    public static List<ItemPedidoDTO> criaListaItemPedidoDTO() {
        return List.of(ItemPedidoHelper.criaItemPedidoDTO());
    }

    public static List<CriaItemPedidoDTO> criaListaCriaItemPedidoDTO() {
        return List.of(new CriaItemPedidoDTO(1L, 1));
    }

    public static List<ItemPedidoRequest> criaListaItemPedidoRequest() {
        return List.of(new ItemPedidoRequest(1L, 1));
    }

    public static List<ItemPedidoResponse> criaListaItemPedidoResponse() {
        return List.of(criaItemPedidoResponse());
    }

    private static ItemPedidoResponse criaItemPedidoResponse() {
        return new ItemPedidoResponse(
                "Produto",
                "Descrição",
                BigDecimal.valueOf(1L),
                1,
                BigDecimal.valueOf(1L)
        );
    }
}
