package br.com.fiap.techchallenge.production.core.usecases.pedido;

import br.com.fiap.techchallenge.production.adapters.repository.PedidoRepository;
import br.com.fiap.techchallenge.production.adapters.repository.jpa.PedidoJpaRepository;
import br.com.fiap.techchallenge.production.adapters.repository.mappers.ItemPedidoMapper;
import br.com.fiap.techchallenge.production.adapters.repository.mappers.PedidoMapper;
import br.com.fiap.techchallenge.production.adapters.repository.models.Pedido;
import br.com.fiap.techchallenge.production.core.domain.entities.enums.StatusPedidoEnum;
import br.com.fiap.techchallenge.production.core.domain.exceptions.EntityNotFoundException;
import br.com.fiap.techchallenge.production.core.dtos.PedidoDTO;
import br.com.fiap.techchallenge.production.core.ports.in.pedido.PublicaPedidoInputPort;
import br.com.fiap.techchallenge.production.utils.PedidoHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AtualizaStatusPedidoUseCaseTest {

    @Mock
    private PedidoJpaRepository pedidoJpaRepository;
    @Mock
    private PublicaPedidoInputPort publicaPedidoInputPort;
    @Mock
    private ItemPedidoMapper itemPedidoMapper;
    @Mock
    private PedidoMapper pedidoMapper;
    @InjectMocks
    private PedidoRepository pedidoRepository;
    private AtualizaStatusPedidoUseCase pedidoUseCase;
    private Pedido pedidoSalvo;
    private AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        pedidoUseCase = new AtualizaStatusPedidoUseCase(pedidoRepository);

        pedidoSalvo = PedidoHelper.criaPedido();
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

        when(pedidoJpaRepository.findById(id)).thenReturn(Optional.of(pedidoSalvo));
        when(pedidoMapper.toPedidoDTO(any(Pedido.class))).thenReturn(PedidoHelper.criaPedidoDTO());
        when(pedidoJpaRepository.save(pedidoSalvo)).thenReturn(pedidoSalvo);

        //Act
        PedidoDTO pedidoAtualizado = pedidoUseCase.atualizarStatus(id, StatusPedidoEnum.PENDENTE_DE_PAGAMENTO);

        //Assert
        assertThat(pedidoAtualizado).isNotNull();
        assertThat(pedidoAtualizado.status()).isEqualTo(StatusPedidoEnum.PENDENTE_DE_PAGAMENTO);
        verify(pedidoJpaRepository, times(1)).findById(anyLong());
        verify(pedidoJpaRepository, times(1)).save(any(Pedido.class));
    }

    @Test
    @DisplayName("Deve lançar EntityNotFoundException quando id informado não existe")
    void deveLancarEntityNotFoundException_QuandoIdIformadoNaoExiste() throws IOException {
        //Arrange
        Long id = 1L;
        String message = String.format("Pedido %s não encontrado", id);
        when(pedidoJpaRepository.findById(id)).thenReturn(Optional.empty());

        //Act
        //Assert
        assertThatThrownBy(() -> pedidoUseCase.atualizarStatus(id, StatusPedidoEnum.RECEBIDO))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage(message);

        verify(pedidoJpaRepository, times(1)).findById(anyLong());
    }
}
