//package br.com.fiap.techchallenge.production.adapters.repository;
//
//import br.com.fiap.techchallenge.production.adapters.repository.jpa.PedidoJpaRepository;
//import br.com.fiap.techchallenge.production.adapters.repository.mappers.PedidoMapper;
//import br.com.fiap.techchallenge.production.adapters.repository.models.Pedido;
//import br.com.fiap.techchallenge.production.core.domain.entities.enums.StatusPedidoEnum;
//import br.com.fiap.techchallenge.production.core.domain.exceptions.EntityNotFoundException;
//import br.com.fiap.techchallenge.production.core.dtos.PedidoDTO;
//import br.com.fiap.techchallenge.production.core.ports.out.pedido.AtualizaStatusPedidoOutputPort;
//import br.com.fiap.techchallenge.production.core.ports.out.pedido.BuscaTodosPedidosOutputPort;
//import br.com.fiap.techchallenge.production.core.ports.out.pedido.BuscaTodosPedidosPorStatusOutputPort;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class PedidoRepository implements AtualizaStatusPedidoOutputPort, BuscaTodosPedidosOutputPort,
//        BuscaTodosPedidosPorStatusOutputPort {
//    private final PedidoMapper pedidoMapper;
//    private final PedidoJpaRepository pedidoJpaRepository;
//
//    public PedidoRepository(PedidoMapper pedidoMapper, PedidoJpaRepository pedidoJpaRepository) {
//        this.pedidoMapper = pedidoMapper;
//        this.pedidoJpaRepository = pedidoJpaRepository;
//    }
//
//    @Override
//    public List<PedidoDTO> buscarTodos() {
//        var listaPedidos = pedidoJpaRepository.findAll();
//
//        return listaPedidos.stream().map(pedidoMapper::toPedidoDTO).toList();
//    }
//
//    @Override
//    public PedidoDTO atualizarStatus(Long id, StatusPedidoEnum status) {
//        var pedidoBuscado = buscarPedidoPorId(id);
//        pedidoBuscado.setStatus(status);
//        var pedido = pedidoJpaRepository.save(pedidoBuscado);
//        return pedidoMapper.toPedidoDTO(pedido);
//    }
//
//    private Pedido buscarPedidoPorId(Long id) {
//        return pedidoJpaRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Pedido " + id + " não encontrado"));
//    }
//
//    @Override
//    public List<PedidoDTO> buscarPedidosPorStatus(StatusPedidoEnum status) {
//        return pedidoJpaRepository.findByStatus(status).stream()
//                .map(pedidoMapper::toPedidoDTO)
//                .toList();
//    }
//}
