package br.com.fiap.techchallenge.production.adapters.gateways;

import br.com.fiap.techchallenge.production.adapters.repository.mappers.PedidoMapper;
import br.com.fiap.techchallenge.production.adapters.web.models.responses.PedidoResponse;
import br.com.fiap.techchallenge.production.core.domain.entities.enums.StatusPedidoEnum;
import br.com.fiap.techchallenge.production.utils.PedidoHelper;
import br.com.fiap.techchallenge.production.utils.ResponseHelper;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PedidoGatewayTest {

    @Mock
    private OkHttpClient okHttpClient;
    @Mock
    private Call call;
    @Mock
    private PedidoMapper pedidoMapper;
    @InjectMocks
    private PedidoGateway pedidoGateway;

    private AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);

        pedidoGateway = new PedidoGateway(okHttpClient, pedidoMapper);
        ReflectionTestUtils.setField(pedidoGateway, "urlApiPedidos", "http://localhost:8081/api/pedidos");
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    @DisplayName("Deve buscar o pedido por id")
    void deveBuscarPedido_QuandoInformadoUmId() throws Exception {
        var pedidoDTO = PedidoHelper.criaPedidoDTO();
        var pedidoResponse = PedidoHelper.criaPedidoResponse();
        var response = ResponseHelper.getResponse(pedidoResponse, 200);

        //Arrange
        when(okHttpClient.newCall(any(Request.class))).thenReturn(call);
        when(call.execute()).thenReturn(response);
        when(pedidoMapper.toPedidoDTO(any(PedidoResponse.class))).thenReturn(pedidoDTO);

        //Act
        var resultado = pedidoGateway.buscarPorId(1L);

        //Assert
        assertThat(resultado).isNotNull().satisfies(req -> {
            assertThat(req.id()).isEqualTo(1L);
            assertThat(req.getNomeCliente()).isEqualTo("cliente1");
            assertThat(req.itens()).hasSize(1);
            assertThat(req.status()).isEqualTo(StatusPedidoEnum.PENDENTE_DE_PAGAMENTO);
            assertThat(req.dataCriacao()).isNotNull();
        });

        verify(okHttpClient).newCall(any(Request.class));
    }

    @Test
    @DisplayName("Deve atualizar o status")
    void deveAtualizarStatus_QuandoInformadoUmId() throws Exception {
        var pedidoDTO = PedidoHelper.criaPedidoDTO();
        var pedidoResponse = PedidoHelper.criaPedidoResponse();
        var response = ResponseHelper.getResponse(pedidoResponse, 200);
        var outroResponse = ResponseHelper.getResponse(pedidoResponse, 200);

        //Arrange
        when(okHttpClient.newCall(any(Request.class))).thenReturn(call);
        when(call.execute()).thenReturn(response)
                .thenReturn(outroResponse);
        when(pedidoMapper.toPedidoDTO(any(PedidoResponse.class))).thenReturn(pedidoDTO);

        //Act
        var resultado = pedidoGateway.atualizarStatus(1L, StatusPedidoEnum.EM_PREPARACAO);

        //Assert
        assertThat(resultado).isNotNull().satisfies(req -> {
            assertThat(req.id()).isEqualTo(1L);
            assertThat(req.getNomeCliente()).isEqualTo("cliente1");
            assertThat(req.itens()).hasSize(1);
            assertThat(req.status()).isEqualTo(StatusPedidoEnum.PENDENTE_DE_PAGAMENTO);
            assertThat(req.dataCriacao()).isBefore(LocalDateTime.now());
        });

        verify(okHttpClient, times(2)).newCall(any(Request.class));
    }

}
