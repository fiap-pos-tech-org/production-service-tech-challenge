package br.com.fiap.techchallenge.production.adapters.web.models.requests;

import jakarta.validation.constraints.NotNull;

public class AtualizaStatusPedidoRequest {
    private String status;

    public AtualizaStatusPedidoRequest() {
    }

    public AtualizaStatusPedidoRequest(String status) {
        this.status = status;
    }

    @NotNull(message = "O campo 'status' é obrigatório")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
