package br.com.fiap.techchallenge.production.core.dtos;

import br.com.fiap.techchallenge.production.core.domain.entities.enums.StatusPedidoEnum;

public record AtualizaStatusPedidoDTO(StatusPedidoEnum status) {
}
