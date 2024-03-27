package br.com.fiap.techchallenge.production.adapters.web.models.responses;

import java.math.BigDecimal;

public class ItemPedidoResponse {
    private String produtoNome;
    private Integer quantidade;

    public ItemPedidoResponse(String produtoNome, Integer quantidade) {
        this.produtoNome = produtoNome;
        this.quantidade = quantidade;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
