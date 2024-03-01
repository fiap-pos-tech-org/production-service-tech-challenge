package br.com.fiap.techchallenge.production.adapters.web.models.requests;

import br.com.fiap.techchallenge.production.core.domain.entities.enums.StatusPedidoEnum;
import br.com.fiap.techchallenge.production.core.dtos.AtualizaStatusPedidoDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class AtualizaStatusPedidoRequest {

    @Schema(example = "EM_PREPARACAO")
    @NotNull(message = "O campo 'status' é obrigatório")
    private StatusPedidoEnum status;

    public AtualizaStatusPedidoRequest() {
    }

    public AtualizaStatusPedidoRequest(StatusPedidoEnum status) {
        this.status = status;
    }

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
