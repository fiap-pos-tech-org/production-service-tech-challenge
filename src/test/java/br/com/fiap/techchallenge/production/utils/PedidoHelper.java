package br.com.fiap.techchallenge.production.utils;

import br.com.fiap.techchallenge.production.adapters.repository.models.Pedido;
import br.com.fiap.techchallenge.production.adapters.web.models.responses.PedidoResponse;
import br.com.fiap.techchallenge.production.core.domain.entities.enums.StatusPedidoEnum;
import br.com.fiap.techchallenge.production.core.dtos.ClienteDTO;
import br.com.fiap.techchallenge.production.core.dtos.ItemPedidoDTO;
import br.com.fiap.techchallenge.production.core.dtos.PedidoDTO;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoHelper {

    public static Pedido criaPedido() {
        return new Pedido(1L, StatusPedidoEnum.PENDENTE_DE_PAGAMENTO, LocalDateTime.now());
    }

    public static List<Pedido> criaListaPedidos() {
        return List.of(criaPedido());
    }

    public static PedidoDTO criaPedidoDTO() {
        ClienteDTO clienteDTO = ClienteHelper.criaClienteDTO();
        List<ItemPedidoDTO> itemsPedido = ItemPedidoHelper.criaListaItemPedidoDTO();
        return new PedidoDTO(1L, itemsPedido, StatusPedidoEnum.PENDENTE_DE_PAGAMENTO, LocalDateTime.now());
    }

    public static List<PedidoDTO> criaListaPedidoDTO() {
        return List.of(criaPedidoDTO());
    }

    public static PedidoResponse criaPedidoResponse() {
        return new PedidoResponse(
                1L,
                ItemPedidoHelper.criaListaItemPedidoResponse(),
                StatusPedidoEnum.PENDENTE_DE_PAGAMENTO,
                LocalDateTime.now()
        );
    }

    public static List<PedidoResponse> criaListaPedidoResponse() {
        PedidoResponse pedidoResponse = criaPedidoResponse();
        return List.of(pedidoResponse);
    }
}
