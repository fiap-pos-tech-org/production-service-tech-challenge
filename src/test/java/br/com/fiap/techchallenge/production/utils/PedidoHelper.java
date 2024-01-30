package br.com.fiap.techchallenge.production.utils;

import br.com.fiap.techchallenge.production.adapters.web.models.requests.AtualizaStatusPedidoRequest;
import br.com.fiap.techchallenge.production.adapters.web.models.responses.PedidoResponse;
import br.com.fiap.techchallenge.production.core.domain.entities.enums.StatusPedidoEnum;
import br.com.fiap.techchallenge.production.core.dtos.AtualizaStatusPedidoDTO;
import br.com.fiap.techchallenge.production.core.dtos.ClienteDTO;
import br.com.fiap.techchallenge.production.core.dtos.ItemPedidoDTO;
import br.com.fiap.techchallenge.production.core.dtos.PedidoDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoHelper {

    public static PedidoDTO criaPedidoDTO() {
        ClienteDTO clienteDTO = ClienteHelper.criaClienteDTO();
        List<ItemPedidoDTO> itemsPedido = ItemPedidoHelper.criaListaItemPedidoDTO();
        return new PedidoDTO(1L, clienteDTO, itemsPedido, StatusPedidoEnum.PENDENTE_DE_PAGAMENTO, BigDecimal.valueOf(1L), LocalDateTime.now());
    }

    public static List<PedidoDTO> criaListaPedidoDTO() {
        return List.of(criaPedidoDTO());
    }

    public static AtualizaStatusPedidoDTO criaAtualizaStatusPedidoDTO() {
        return new AtualizaStatusPedidoDTO(StatusPedidoEnum.RECEBIDO);
    }

    public static PedidoResponse criaPedidoResponse() {
        return new PedidoResponse(
                1L,
                "Cliente",
                ItemPedidoHelper.criaListaItemPedidoResponse(),
                StatusPedidoEnum.PENDENTE_DE_PAGAMENTO,
                BigDecimal.valueOf(1L),
                LocalDateTime.now()
        );
    }

    public static List<PedidoResponse> criaListaPedidoResponse() {
        PedidoResponse pedidoResponse = criaPedidoResponse();
        return List.of(pedidoResponse);
    }

    public static AtualizaStatusPedidoRequest criaAtualizaStatusPedidoRequest() {
        return new AtualizaStatusPedidoRequest(StatusPedidoEnum.RECEBIDO);
    }
}
