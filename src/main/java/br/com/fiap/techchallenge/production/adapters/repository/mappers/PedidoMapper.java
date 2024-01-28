package br.com.fiap.techchallenge.production.adapters.repository.mappers;

import br.com.fiap.techchallenge.production.adapters.repository.jpa.ClienteJpaRepository;
import br.com.fiap.techchallenge.production.adapters.repository.models.Pedido;
import br.com.fiap.techchallenge.production.adapters.web.models.responses.PedidoResponse;
import br.com.fiap.techchallenge.production.core.dtos.ClienteDTO;
import br.com.fiap.techchallenge.production.core.dtos.PedidoDTO;
import br.com.fiap.techchallenge.production.core.domain.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoMapper {
    private final ItemPedidoMapper itemPedidoMapper;
    private final ClienteJpaRepository clienteJpaRepository;
    public PedidoMapper(ItemPedidoMapper itemPedidoMapper, ClienteJpaRepository clienteJpaRepository) {
        this.itemPedidoMapper = itemPedidoMapper;
        this.clienteJpaRepository = clienteJpaRepository;
    }

    public Pedido toPedido(PedidoDTO pedidoIn){
        var cliente = pedidoIn.cliente() != null
                ? clienteJpaRepository.findById(pedidoIn.cliente().id())
                    .orElseThrow(() -> new EntityNotFoundException("Cliente "+pedidoIn.id()+" não encontrado"))
                : null;

        var pedido = cliente != null
                ? new Pedido(pedidoIn.status(), cliente, pedidoIn.dataCriacao(), pedidoIn.valorTotal())
                : new Pedido(pedidoIn.status(), pedidoIn.dataCriacao(), pedidoIn.valorTotal());

        var itemPedido = itemPedidoMapper.toItemPedido(pedido, pedidoIn.itens());
        pedido.setItens(itemPedido);
        return pedido;

    }

    public PedidoDTO toPedidoDTO(Pedido pedido) {
        var cliente = pedido.getCliente();
        var clienteDTO = cliente!= null
                ? new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getEmail())
                : null;
        var listaItemPedidoOut = itemPedidoMapper.toItemPedidoResponse(pedido.getItens());

        return new PedidoDTO(
                pedido.getId(), clienteDTO, listaItemPedidoOut, pedido.getStatus(), pedido.getValorTotal(), pedido.getData());

    }

    public PedidoDTO pedidoResponseToDTO(PedidoResponse pedidoResponse) {
        return new PedidoDTO(pedidoResponse);
    }

    public List<PedidoDTO> toListaPedidoDTO(List<PedidoResponse> pedidoResponse) {
        return pedidoResponse.stream().map(this::pedidoResponseToDTO).toList();
    }
}
