package br.com.fiap.techchallenge.production.adapters.repository.models;

import br.com.fiap.techchallenge.production.core.domain.entities.enums.StatusPedidoEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatusPedidoEnum status;

    private LocalDateTime data;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, targetEntity = ItemPedido.class)
    private List<ItemPedido> itens = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        data = LocalDateTime.now();
        status = StatusPedidoEnum.EM_PREPARACAO;
    }

    public Pedido() {
    }

    public Pedido(StatusPedidoEnum status, LocalDateTime data, List<ItemPedido> itens) {
        this.status = status;
        this.data = data;
        this.itens = itens;
    }

    public Pedido(Long id, StatusPedidoEnum status, LocalDateTime data) {
        this.id = id;
        this.status = status;
        this.data = data;
    }

    public Pedido(Long id, StatusPedidoEnum status, List<ItemPedido> itens, LocalDateTime data) {
        this.id = id;
        this.status = status;
        this.itens = itens;
        this.data = data;
    }

    public Pedido(StatusPedidoEnum status, LocalDateTime data) {
        this.status = status;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }
}
