package br.com.fiap.techchallenge.production.core.usecases.pedido;

import br.com.fiap.techchallenge.production.adapters.gateways.PedidoGateway;
import br.com.fiap.techchallenge.production.adapters.repository.mappers.PedidoMapper;
import br.com.fiap.techchallenge.production.adapters.web.models.responses.PedidoResponse;
import br.com.fiap.techchallenge.production.core.dtos.PedidoDTO;
import br.com.fiap.techchallenge.production.utils.PedidoHelper;
import br.com.fiap.techchallenge.production.utils.ResponseHelper;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class BuscaPedidosPorPrioridadeUseCaseTest {

    @Mock
    private OkHttpClient httpClient;
    @Mock
    private Call call;
    private PedidoMapper pedidoMapper = new PedidoMapper();
    private PedidoGateway pedidoGateway;
    private BuscaPedidosPorPrioridadeUseCase pedidoUseCase;
    private List<PedidoResponse> pedidosSalvos;
    private AutoCloseable openMocks;


    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        pedidoGateway = new PedidoGateway(httpClient, pedidoMapper);
        ReflectionTestUtils.setField(pedidoGateway, "urlApiPedidos", "http://localhost:8081/api/pedidos");
        pedidoUseCase = new BuscaPedidosPorPrioridadeUseCase(pedidoGateway);

        pedidosSalvos = PedidoHelper.criaListaPedidoResponse();
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }


    @Test
    @DisplayName("Deve buscar todos os pedidos quando o método buscarTodos for invocado")
    void deveBuscarTodosOsPedidos_QuandoMetodoBuscarTodosForInvocado() throws IOException {
        //Arrange
        when(httpClient.newCall(any(Request.class))).thenReturn(call);
        when(call.execute()).thenReturn(ResponseHelper.getResponse(pedidosSalvos, 200));

        //Act
        List<PedidoDTO> listaPedidos = pedidoUseCase.buscarPorPrioridade();

        //Assert
        assertThat(listaPedidos).isNotNull();
        assertThat(listaPedidos).allSatisfy(pedido -> {
            assertThat(pedido).isNotNull();
            assertThat(pedido.id()).isNotNull();
            assertThat(pedido.cliente()).isNotNull();
            assertThat(pedido.itens()).isNotNull();
            assertThat(pedido.status()).isNotNull();
            assertThat(pedido.valorTotal()).isNotNull();
        });

        verify(httpClient, times(1)).newCall(any(Request.class));
        verify(call, times(1)).execute();
    }
}
