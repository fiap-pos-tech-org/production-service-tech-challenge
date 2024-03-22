package br.com.fiap.techchallenge.production.core.dtos;

import br.com.fiap.techchallenge.production.adapters.web.models.responses.PedidoResponse;
import br.com.fiap.techchallenge.production.core.domain.entities.enums.StatusPedidoEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoDTO(
        Long id,
        List<ItemPedidoDTO> itens,
        StatusPedidoEnum status,
        LocalDateTime dataCriacao
) {
    public PedidoDTO(PedidoResponse pedidoResponse) {
        this(
                pedidoResponse.getId(),
                pedidoResponse.getItens().stream().map(ItemPedidoDTO::new).toList(),
                pedidoResponse.getStatus(),
                pedidoResponse.getData()
        );
    }

}
