package br.com.fiap.techchallenge.production.core.dtos;

import br.com.fiap.techchallenge.production.core.domain.entities.enums.StatusPedidoEnum;

import java.util.List;

public record CriaPedidoDTO(
        Long idPedido,
        List<ItemPedidoDTO> itens,
        StatusPedidoEnum status
) {
}
