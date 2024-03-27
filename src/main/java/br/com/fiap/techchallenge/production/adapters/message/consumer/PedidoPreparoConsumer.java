package br.com.fiap.techchallenge.production.adapters.message.consumer;

import br.com.fiap.techchallenge.production.adapters.repository.jpa.PedidoJpaRepository;
import br.com.fiap.techchallenge.production.adapters.repository.models.Pedido;
import br.com.fiap.techchallenge.production.core.dtos.CriaPedidoDTO;
import br.com.fiap.techchallenge.production.core.ports.in.pedido.CriaPedidoInputPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PedidoPreparoConsumer {

    private final Logger logger = LogManager.getLogger(PedidoPreparoConsumer.class);
    private final CriaPedidoInputPort criaPedidoInputPort;
    private final ObjectMapper mapper;

    public PedidoPreparoConsumer(CriaPedidoInputPort criaPedidoInputPort, ObjectMapper mapper) {
        this.criaPedidoInputPort = criaPedidoInputPort;
        this.mapper = mapper;
    }

    @JmsListener(destination = "${aws.sqs.fila-producao}")
    public void receiveMessage(@Payload String mensagem) {
        logger.info("mensagem recebida do serviço pedido: {}", mensagem);

        CriaPedidoDTO pedido;
        try {
            pedido = mapper.readValue(mensagem, CriaPedidoDTO.class);
        } catch (JsonProcessingException e) {
            logger.error("erro ao serializar mensagem: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        criaPedidoInputPort.criar(pedido);
    }
}
