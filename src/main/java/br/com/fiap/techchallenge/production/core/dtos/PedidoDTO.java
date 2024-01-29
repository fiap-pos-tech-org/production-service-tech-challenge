package br.com.fiap.techchallenge.production.core.dtos;

import br.com.fiap.techchallenge.production.adapters.web.models.responses.PedidoResponse;
import br.com.fiap.techchallenge.production.core.domain.entities.enums.StatusPedidoEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoDTO(
        Long id,
        ClienteDTO cliente,
        List<ItemPedidoDTO> itens,
        StatusPedidoEnum status,
        BigDecimal valorTotal,
        LocalDateTime dataCriacao
) {
    public String getNomeCliente() {
        return cliente != null ? cliente.nome() : null;
    }

    public PedidoDTO(PedidoResponse pedidoResponse) {
        this(
                pedidoResponse.getId(),
                new ClienteDTO(pedidoResponse.getClienteNome()),
                pedidoResponse.getItens().stream().map(ItemPedidoDTO::new).toList(),
                pedidoResponse.getStatus(),
                pedidoResponse.getValorTotal(),
                pedidoResponse.getData()
        );
    }

}
