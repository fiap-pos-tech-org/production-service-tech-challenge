package br.com.fiap.techchallenge.production.adapters.web;

import br.com.fiap.techchallenge.production.adapters.web.mappers.PedidoMapper;
import br.com.fiap.techchallenge.production.adapters.web.models.requests.AtualizaStatusPedidoRequest;
import br.com.fiap.techchallenge.production.adapters.web.models.responses.PedidoResponse;
import br.com.fiap.techchallenge.production.core.domain.entities.enums.StatusPedidoEnum;
import br.com.fiap.techchallenge.production.core.ports.in.pedido.AtualizaStatusPedidoInputPort;
import br.com.fiap.techchallenge.production.core.ports.in.pedido.BuscaPedidosOrdenadosPorPrioridadeInputPort;
import br.com.fiap.techchallenge.production.core.ports.in.pedido.BuscaTodosPedidosPorStatusInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pedido", description = "APIs para gerenciamento de Pedido")
@Validated
@RestController
@RequestMapping("/pedidos")
public class PedidoController extends ControllerBase {
    private final AtualizaStatusPedidoInputPort atualizaStatusPedidoInputPort;
    private final BuscaTodosPedidosPorStatusInputPort buscaTodosPedidosPorStatusInputPort;
    private final BuscaPedidosOrdenadosPorPrioridadeInputPort buscaPedidosOrdenadosPorPrioridadeInputPort;
    private final PedidoMapper pedidoMapper;

    public PedidoController(AtualizaStatusPedidoInputPort atualizaStatusPedidoInputPort,
                            BuscaPedidosOrdenadosPorPrioridadeInputPort buscaPedidosOrdenadosPorPrioridadeInputPort,
                            BuscaTodosPedidosPorStatusInputPort buscaTodosPedidosPorStatusInputPort,
                            PedidoMapper pedidoMapper
    ) {
        this.atualizaStatusPedidoInputPort = atualizaStatusPedidoInputPort;
        this.buscaPedidosOrdenadosPorPrioridadeInputPort = buscaPedidosOrdenadosPorPrioridadeInputPort;
        this.buscaTodosPedidosPorStatusInputPort = buscaTodosPedidosPorStatusInputPort;
        this.pedidoMapper = pedidoMapper;
    }

    @Operation(summary = "Busca pedidos para serem exibidos na fila de preparação")
    @GetMapping("/fila-producao")
    public ResponseEntity<List<PedidoResponse>> buscarTodosPedidosPorPrioridade() {
        var pedidosOut = buscaPedidosOrdenadosPorPrioridadeInputPort.buscarPorPrioridade();
        var listPedidoResponse = pedidoMapper.toPedidoListResponse(pedidosOut);
        return ResponseEntity.ok(listPedidoResponse);
    }

    @Operation(summary = "Atualiza status de um  pedido")
    @PatchMapping("/{id}")
    public ResponseEntity<PedidoResponse> atualizaStatus(@Parameter(example = "1")
                                                         @PathVariable("id")
                                                         @Pattern(regexp = "^\\d*$", message = "O id do pedido deve conter apenas números") String id,
                                                         @RequestBody AtualizaStatusPedidoRequest pedidoRequest) {
        var pedidoOut = atualizaStatusPedidoInputPort.atualizarStatus(Long.parseLong(id), pedidoRequest.toAtualizaStatusPedidoDTO());
        var pedidoResponse = pedidoMapper.toPedidoResponse(pedidoOut);
        var uri = getExpandedCurrentUri("/{id}", pedidoResponse.getId());
        return ResponseEntity.created(uri).body(pedidoResponse);
    }

    @Operation(summary = "Busca todos os pedidos por status")
    @GetMapping(value = "/status/{status}")
    public ResponseEntity<List<PedidoResponse>> buscarTodos(@Parameter(example = "PENDENTE_DE_PAGAMENTO") @PathVariable("status") String status) {
        var pedidosOut = buscaTodosPedidosPorStatusInputPort.buscarTodosStatus(StatusPedidoEnum.fromString(status))
                .stream()
                .map(pedidoMapper::toPedidoResponse)
                .toList();
        return ResponseEntity.ok(pedidosOut);
    }
}
