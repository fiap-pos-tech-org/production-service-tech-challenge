package br.com.fiap.techchallenge.production.core.dtos;

import br.com.fiap.techchallenge.production.core.domain.entities.enums.StatusPedidoEnum;

import java.util.List;

public class MensagemPedidoProntoDTO extends MensagemDTOBase {

    private StatusPedidoEnum status;
    public MensagemPedidoProntoDTO() {
    }

    public MensagemPedidoProntoDTO(Long idPedido, StatusPedidoEnum status) {
        super(idPedido);
        this.status = status;
    }

    public StatusPedidoEnum getStatus() {
        return status;
    }

    public void setStatus(StatusPedidoEnum status) {
        this.status = status;
    }
}
