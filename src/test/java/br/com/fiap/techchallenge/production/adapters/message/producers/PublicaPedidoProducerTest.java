package br.com.fiap.techchallenge.production.adapters.message.producers;

import br.com.fiap.techchallenge.production.core.domain.entities.enums.StatusPedidoEnum;
import br.com.fiap.techchallenge.production.core.dtos.MensagemPedidoProntoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class PublicaPedidoProducerTest {

    @Mock
    private ObjectMapper mapper;
    @Mock
    private SnsClient snsClient;
    @InjectMocks
    private PublicaPedidoProducer publicaPedidoProducer;
    private MensagemPedidoProntoDTO mensagemPedidoPronto;
    private AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        mensagemPedidoPronto = new MensagemPedidoProntoDTO(1L, StatusPedidoEnum.PRONTO);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    @DisplayName("Deve publicar mensagem em tópico Fifo quando objeto mensagem e ARN do tópico forem informados")
    void devePublicarMensagemEmTopicoFifo_QuandoObjetoMensagemEArnTopicoInformados() throws JsonProcessingException {
        //Arrange
        PublishResponse response = PublishResponse.builder().messageId(UUID.randomUUID().toString()).build();
        when(snsClient.publish(any(PublishRequest.class))).thenReturn(response);
        when(mapper.writeValueAsString(any())).thenReturn(new ObjectMapper().writeValueAsString(mensagemPedidoPronto));

        //Act
        publicaPedidoProducer.publicarFifo(mensagemPedidoPronto, "arn::0001");

        //Assert
        verify(snsClient, times(1)).publish(any(PublishRequest.class));
    }

    @Test
    @DisplayName("Deve lançar RuntimeException quando objeto mensagem não for serializado topico Fifo")
    void deveLancarRuntimeException_QuandoObjetoMensagemNaoForSerializadoTopicoFifo() throws JsonProcessingException {
        //Arrange
        when(mapper.writeValueAsString(any(MensagemPedidoProntoDTO.class))).thenThrow(JsonProcessingException.class);

        //Act
        //Assert
        assertThatThrownBy(() -> publicaPedidoProducer.publicarFifo(mensagemPedidoPronto, "arn::0001"))
                .isInstanceOf(RuntimeException.class);

        verify(snsClient, times(0)).publish(any(PublishRequest.class));
    }
}
