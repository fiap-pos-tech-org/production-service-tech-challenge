package br.com.fiap.techchallenge.production.core.domain.entities;

import java.math.BigDecimal;

public class ItemPedido {
    private String produtoNome;
    private BigDecimal valorUnitario;
    private Integer quantidade;

    public ItemPedido(String produtoNome, BigDecimal valorUnitario, Integer quantidade) {
        this.produtoNome = produtoNome;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorTotal() {
        return BigDecimal.valueOf(quantidade).multiply(valorUnitario);
    }
}
