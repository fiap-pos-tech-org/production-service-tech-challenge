package br.com.fiap.techchallenge.production.adapters.web.mappers;

import br.com.fiap.techchallenge.production.utils.PedidoHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PedidoMapperTest {
    @Test
    @DisplayName("Deve retornar um objeto tipo PedidoResponse quando receber como parâmetro um PedidoDTO")
    void deveRetornarUmObjetoTipoPedidoResponse_QuandoReceberComoParametroUmPedidoDTO() {
        //Arrange
        PedidoMapper pedidoMapper = new PedidoMapper();
        //Act
        var pedidoResponse = pedidoMapper.toPedidoResponse(PedidoHelper.criaPedidoDTO());
        //Assert
        assertThat(pedidoResponse).isNotNull();
    }

    @Test
    @DisplayName("Deve retornar uma lista de objetos tipo PedidoResponse quando receber como parâmetro uma lista de PedidoDTO")
    void deveRetornarUmaListaDeObjetosTipoPedidoResponse_QuandoReceberComoParametroUmaListaDePedidoDTO() {
        //Arrange
        PedidoMapper pedidoMapper = new PedidoMapper();
        //Act
        var pedidoResponse = pedidoMapper.toPedidoListResponse(PedidoHelper.criaListaPedidoDTO());
        //Assert
        assertThat(pedidoResponse).isNotNull();
    }
}
