package br.com.fiap.techchallenge.production.adapters.web.models.requests;

import br.com.fiap.techchallenge.production.core.dtos.AtualizaStatusPedidoDTO;
import br.com.fiap.techchallenge.production.core.domain.entities.enums.StatusPedidoEnum;
import jakarta.validation.constraints.NotNull;

public class AtualizaStatusPedidoRequest {
    private StatusPedidoEnum status;

    public AtualizaStatusPedidoRequest() {
    }

    public AtualizaStatusPedidoRequest(StatusPedidoEnum status) {
        this.status = status;
    }

    @NotNull(message = "O campo 'status' é obrigatório")
    public StatusPedidoEnum getStatus() {
        return status;
    }

    public void setStatus(StatusPedidoEnum status) {
        this.status = status;
    }

    public AtualizaStatusPedidoDTO toAtualizaStatusPedidoDTO() {
        return new AtualizaStatusPedidoDTO(
            status
        );
    }
}
