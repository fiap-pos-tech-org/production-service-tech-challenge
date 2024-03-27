package br.com.fiap.techchallenge.production.adapters.message.consumers;


import br.com.fiap.techchallenge.production.adapters.message.consumer.PedidoPreparoConsumer;
import br.com.fiap.techchallenge.production.core.dtos.CriaPedidoDTO;
import br.com.fiap.techchallenge.production.core.ports.in.pedido.CriaPedidoInputPort;
import br.com.fiap.techchallenge.production.utils.PedidoHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PedidoPreparoConsumerTest {

    @Autowired
    private ObjectMapper mapper;
    @Mock
    private CriaPedidoInputPort criaPedidoInputPort;
    private PedidoPreparoConsumer pedidoProntoConsumer;
    private AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        pedidoProntoConsumer = new PedidoPreparoConsumer(criaPedidoInputPort, mapper);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    @DisplayName("Deve criar pedido com status EM_PREPARACAO quando receber mensagem com o status correto")
    void deveCriarPedidoComStatusEmPreparacao_QuandoReceberMensagemComStatusCorreto() throws JsonProcessingException {
        //Arrange
        var msgString = "{\"idPedido\": 1, \"status\": \"PRONTO\"}";
        when(criaPedidoInputPort.criar(any(CriaPedidoDTO.class))).thenReturn(PedidoHelper.criaPedidoDTO());

        //Act
        pedidoProntoConsumer.receiveMessage(msgString);

        //Assert
        verify(criaPedidoInputPort, times(1)).criar(any(CriaPedidoDTO.class));
    }

    @Test
    @DisplayName("Deve lancar RuntimeException quando nao for possivel serializar mensagem")
    void deveLancarRuntimeException_QuandoNaoForPossivelSerializarMensagem() {
        //Arrange
        //Act
        assertThatThrownBy(() -> pedidoProntoConsumer.receiveMessage("{\\}"))
                .isInstanceOf(RuntimeException.class);

        //Assert
        verify(criaPedidoInputPort, never()).criar(any(CriaPedidoDTO.class));
    }
}
