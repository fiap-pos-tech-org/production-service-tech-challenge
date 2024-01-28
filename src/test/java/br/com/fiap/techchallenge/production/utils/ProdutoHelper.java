package br.com.fiap.techchallenge.production.utils;

import br.com.fiap.techchallenge.production.adapters.repository.models.Produto;
import br.com.fiap.techchallenge.production.core.domain.entities.enums.CategoriaEnum;
import br.com.fiap.techchallenge.production.core.dtos.ProdutoDTO;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoHelper {

    public static Produto criaProduto() {
        return new Produto(1L, "x-tudo", CategoriaEnum.LANCHE, BigDecimal.valueOf(1L), "muito bom");
    }

    public static ProdutoDTO criaProdutoDTO() {
        return new ProdutoDTO("dogão-brabo", CategoriaEnum.LANCHE, BigDecimal.valueOf(6L), "topperson");
    }

    public static List<Produto> criaListaProdutos() {
        return List.of(ProdutoHelper.criaProduto());
    }

    public static List<ProdutoDTO> criaListaProdutoDTO() {
        return List.of(ProdutoHelper.criaProdutoDTO());
    }
}
