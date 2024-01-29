package br.com.fiap.techchallenge.production.core.usecases.pedido;

import br.com.fiap.techchallenge.production.adapters.gateways.PedidoGateway;
import br.com.fiap.techchallenge.production.adapters.repository.mappers.PedidoMapper;
import br.com.fiap.techchallenge.production.adapters.web.handlers.ErrorDetails;
import br.com.fiap.techchallenge.production.adapters.web.models.responses.PedidoResponse;
import br.com.fiap.techchallenge.production.core.domain.entities.enums.StatusPedidoEnum;
import br.com.fiap.techchallenge.production.core.domain.exceptions.NotFoundException;
import br.com.fiap.techchallenge.production.core.dtos.AtualizaStatusPedidoDTO;
import br.com.fiap.techchallenge.production.core.dtos.PedidoDTO;
import br.com.fiap.techchallenge.production.utils.ErrorDetailsHelper;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AtualizaStatusPedidoUseCaseTest {

    @Mock
    private OkHttpClient httpClient;
    @Mock
    private Call call;
    private PedidoMapper pedidoMapper = new PedidoMapper();
    private PedidoGateway pedidoGateway;
    private AtualizaStatusPedidoUseCase pedidoUseCase;
    private PedidoResponse pedidoSalvo;
    private AtualizaStatusPedidoDTO atualizaStatusPedidoDTO;
    private AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        pedidoGateway = new PedidoGateway(httpClient, pedidoMapper);
        ReflectionTestUtils.setField(pedidoGateway, "urlApiPedidos", "http://localhost:8081/api/pedidos");
        pedidoUseCase = new AtualizaStatusPedidoUseCase(pedidoGateway);

        pedidoSalvo = PedidoHelper.criaPedidoResponse();
        atualizaStatusPedidoDTO = PedidoHelper.criaAtualizaStatusPedidoDTO();
    }

    @AfterEach
    void releaseMocks() throws Exception {
        openMocks.close();
    }

    @Test
    @DisplayName("Deve atualizar status do pedido quando dados informados corretamente")
    void deveAtualizarStatusPedido_QuandoDadosInformadosCorretamente() throws IOException {
        //Arrange
        Long id = 1L;
        pedidoSalvo.setStatus(StatusPedidoEnum.RECEBIDO);
        when(httpClient.newCall(any(Request.class))).thenReturn(call);
        when(call.execute()).thenReturn(ResponseHelper.getResponse(pedidoSalvo, 200))
                .thenReturn(ResponseHelper.getResponse(pedidoSalvo, 200));

        //Act
        PedidoDTO pedidoAtualizado = pedidoUseCase.atualizarStatus(id, atualizaStatusPedidoDTO);

        //Assert
        assertThat(pedidoAtualizado).isNotNull();
        assertThat(pedidoAtualizado.status()).isEqualTo(atualizaStatusPedidoDTO.status());

        verify(httpClient, times(2)).newCall(any(Request.class));
        verify(call, times(2)).execute();
    }

    @Test
    @DisplayName("Deve lançar EntityNotFoundException quando id informado não existe")
    void deveLancarEntityNotFoundException_QuandoIdIformadoNaoExiste() throws IOException {
        //Arrange
        Long id = 1L;
        int statusCode = 404;
        ErrorDetails errorDetails = ErrorDetailsHelper.criarErrorDetails(statusCode);
        when(httpClient.newCall(any(Request.class))).thenReturn(call);
        when(call.execute()).thenReturn( ResponseHelper.getResponse(errorDetails, statusCode));

        //Act
        //Assert
        assertThatThrownBy(() -> pedidoUseCase.atualizarStatus(id, atualizaStatusPedidoDTO))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("alguma exception");

        verify(httpClient, times(1)).newCall(any(Request.class));
        verify(call, times(1)).execute();
    }
}
