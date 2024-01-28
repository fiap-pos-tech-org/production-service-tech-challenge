package br.com.fiap.techchallenge.production;

import br.com.fiap.techchallenge.production.adapters.web.models.requests.ItemPedidoRequest;
import br.com.fiap.techchallenge.production.adapters.web.models.requests.PedidoRequest;
import br.com.fiap.techchallenge.production.adapters.web.models.responses.PedidoResponse;
import br.com.fiap.techchallenge.production.core.domain.entities.enums.StatusPedidoEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoTestBase {

    private PedidoTestBase() {
    }

    public static PedidoRequest criarPedidoRequest(Long clientId, Long produtoId) {
        var pedidoRequest = new PedidoRequest();
        pedidoRequest.setClienteId(clientId);
        pedidoRequest.setItens(criarItensPedidoRequest(produtoId));
        return pedidoRequest;
    }

    private static List<ItemPedidoRequest> criarItensPedidoRequest(Long produtoId) {
        var itemPedidoRequest = new ItemPedidoRequest();
        itemPedidoRequest.setProdutoId(produtoId);
        itemPedidoRequest.setQuantidade(1);
        return List.of(itemPedidoRequest);
    }

    public static PedidoResponse criarPedidoResponse() {
        return new PedidoResponse(1L, "Cliente Teste", null,
                StatusPedidoEnum.EM_PREPARACAO, BigDecimal.valueOf(1L), LocalDateTime.now());
    }
}
