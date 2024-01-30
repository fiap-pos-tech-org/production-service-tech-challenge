package br.com.fiap.techchallenge.production.adapters.web.controllers;

import br.com.fiap.techchallenge.production.adapters.web.PedidoController;
import br.com.fiap.techchallenge.production.adapters.web.handlers.ExceptionsHandler;
import br.com.fiap.techchallenge.production.adapters.web.mappers.PedidoMapper;
import br.com.fiap.techchallenge.production.core.domain.entities.enums.StatusPedidoEnum;
import br.com.fiap.techchallenge.production.core.domain.exceptions.EntityNotFoundException;
import br.com.fiap.techchallenge.production.core.dtos.PedidoDTO;
import br.com.fiap.techchallenge.production.core.ports.in.pedido.AtualizaStatusPedidoInputPort;
import br.com.fiap.techchallenge.production.core.ports.in.pedido.BuscaPedidosOrdenadosPorPrioridadeInputPort;
import br.com.fiap.techchallenge.production.core.ports.in.pedido.BuscaTodosPedidosPorStatusInputPort;
import br.com.fiap.techchallenge.production.utils.ObjectParaJsonMapper;
import br.com.fiap.techchallenge.production.utils.PedidoHelper;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PedidoControllerTest {
    private MockMvc mockMvc;
    private AutoCloseable openMocks;
    @Mock
    private AtualizaStatusPedidoInputPort atualizaStatusPedidoInputPort;
    @Mock
    private BuscaPedidosOrdenadosPorPrioridadeInputPort buscaPedidosOrdenadosPorPrioridadeInputPort;
    @Mock
    private BuscaTodosPedidosPorStatusInputPort buscaTodosPedidosPorStatusInputPort;
    @Mock
    private PedidoMapper pedidoMapperWeb;
    @InjectMocks
    private PedidoController pedidoController;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pedidoController)
                .setControllerAdvice(new ExceptionsHandler())
                .build();
    }

    @AfterEach
    void releaseMocks() throws Exception {
        openMocks.close();
    }

    @Nested
    @DisplayName("Busca pedidos")
    class BuscaPedidos {

        @Test
        @DisplayName("Deve retornar todos os pedidos por status")
        void deveRetornarTodosPedidosPorStatus() throws Exception {
            //Arrange
            when(buscaTodosPedidosPorStatusInputPort.buscarTodosStatus(any(StatusPedidoEnum.class))).thenReturn(PedidoHelper.criaListaPedidoDTO());
            when(pedidoMapperWeb.toPedidoResponse(any(PedidoDTO.class))).thenReturn(PedidoHelper.criaPedidoResponse());

            //Act
            //Assert
            mockMvc.perform(get("/pedidos/status/{status}", "PENDENTE_DE_PAGAMENTO"))
                    .andExpect(status().isOk())
                    .andExpectAll(
                            jsonPath("$").isNotEmpty(),
                            jsonPath("$").isArray(),
                            jsonPath("$[0].id").value(1),
                            jsonPath("$[0].id").isNumber(),
                            jsonPath("$[0].clienteNome").value("Cliente"),
                            jsonPath("$[0].clienteNome").isString(),
                            jsonPath("$[0].itens").isNotEmpty(),
                            jsonPath("$[0].itens").isArray(),
                            jsonPath("$[0].status").value("PENDENTE_DE_PAGAMENTO"),
                            jsonPath("$[0].status").isString(),
                            jsonPath("$[0].valorTotal").value(1),
                            jsonPath("$[0].valorTotal").isNumber(),
                            jsonPath("$[0].data").isNotEmpty(),
                            jsonPath("$[0].data").isArray()
                    );
        }

        @Test
        @DisplayName("Deve retornar todos os pedidos por prioridade")
        void deveRetornarTodosPedidosPorPrioridade() throws Exception {
            //Arrange
            when(buscaPedidosOrdenadosPorPrioridadeInputPort.buscarPorPrioridade()).thenReturn(PedidoHelper.criaListaPedidoDTO());
            when(pedidoMapperWeb.toPedidoListResponse(anyList())).thenReturn(PedidoHelper.criaListaPedidoResponse());

            //Act
            //Assert
            mockMvc.perform(get("/pedidos/fila-producao"))
                    .andExpect(status().isOk())
                    .andExpectAll(
                            jsonPath("$").isNotEmpty(),
                            jsonPath("$").isArray(),
                            jsonPath("$[0].id").value(1),
                            jsonPath("$[0].id").isNumber(),
                            jsonPath("$[0].clienteNome").value("Cliente"),
                            jsonPath("$[0].clienteNome").isString(),
                            jsonPath("$[0].itens").isNotEmpty(),
                            jsonPath("$[0].itens").isArray(),
                            jsonPath("$[0].status").value("PENDENTE_DE_PAGAMENTO"),
                            jsonPath("$[0].status").isString(),
                            jsonPath("$[0].valorTotal").value(1),
                            jsonPath("$[0].valorTotal").isNumber(),
                            jsonPath("$[0].data").isNotEmpty(),
                            jsonPath("$[0].data").isArray()
                    );
        }
    }

    @Nested
    @DisplayName("Atualiza status de um pedido")
    class AtualizaStatusPedido {
        @Test
        @DisplayName("Deve atualizar status de um pedido")
        void deveAtualizarStatusPedido() throws Exception {
            //Arrange
            Long id = 1L;
            when(atualizaStatusPedidoInputPort.atualizarStatus(any(), any())).thenReturn(PedidoHelper.criaPedidoDTO());
            when(pedidoMapperWeb.toPedidoResponse(any())).thenReturn(PedidoHelper.criaPedidoResponse());

            //Act
            //Assert
            mockMvc.perform(patch("/pedidos/{id}", id)
                            .contentType("application/json")
                            .content(ObjectParaJsonMapper.converte(PedidoHelper.criaAtualizaStatusPedidoRequest())))
                    .andExpect(status().isCreated())
                    .andExpectAll(
                            jsonPath("$.id").value(id),
                            jsonPath("$.id").isNumber(),
                            jsonPath("$.clienteNome").value("Cliente"),
                            jsonPath("$.clienteNome").isString(),
                            jsonPath("$.itens").isNotEmpty(),
                            jsonPath("$.itens").isArray(),
                            jsonPath("$.status").value("PENDENTE_DE_PAGAMENTO"),
                            jsonPath("$.status").isString(),
                            jsonPath("$.valorTotal").value(1),
                            jsonPath("$.valorTotal").isNumber(),
                            jsonPath("$.data").isNotEmpty(),
                            jsonPath("$.data").isArray()
                    );
        }

        @Test
        @DisplayName("Deve retornar NotFound ao atualizar status de um pedido")
        void deveRetornarNotFoundAoAtualizarStatusPedido() throws Exception {
            //Arrange
            Long id = 1L;
            String mensagem = String.format("Pedido %s não encontrado", id);
            when(atualizaStatusPedidoInputPort.atualizarStatus(any(), any())).thenThrow(new EntityNotFoundException(mensagem));

            //Act
            //Assert
            mockMvc.perform(patch("/pedidos/{id}", 1)
                            .contentType("application/json")
                            .content(ObjectParaJsonMapper.converte(PedidoHelper.criaAtualizaStatusPedidoRequest())))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.message").value(mensagem));
        }

    }
}
