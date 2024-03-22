package br.com.fiap.techchallenge.production.core.dtos;

import br.com.fiap.techchallenge.production.adapters.web.models.responses.ItemPedidoResponse;

import java.math.BigDecimal;

public record ItemPedidoDTO(
        String produtoNome,
        Integer quantidade
) {

    public ItemPedidoDTO(ItemPedidoResponse itemPedidoResponse) {
        this(itemPedidoResponse.getProdutoNome(),itemPedidoResponse.getQuantidade());
    }
}
