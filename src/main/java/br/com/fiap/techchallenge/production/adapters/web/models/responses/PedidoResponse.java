package br.com.fiap.techchallenge.production.adapters.web.models.responses;

import br.com.fiap.techchallenge.production.core.domain.entities.enums.StatusPedidoEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoResponse {
    private Long id;
    private List<ItemPedidoResponse> itens;
    private StatusPedidoEnum status;
    private LocalDateTime data;

    public PedidoResponse(Long id, List<ItemPedidoResponse> itens, StatusPedidoEnum status, LocalDateTime data) {
        this.id = id;
        this.itens = itens;
        this.status = status;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemPedidoResponse> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoResponse> itens) {
        this.itens = itens;
    }

    public StatusPedidoEnum getStatus() {
        return status;
    }

    public void setStatus(StatusPedidoEnum status) {
        this.status = status;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
