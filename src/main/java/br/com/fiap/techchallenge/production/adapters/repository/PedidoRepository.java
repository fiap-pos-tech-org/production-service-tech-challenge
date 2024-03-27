package br.com.fiap.techchallenge.production.adapters.repository;

import br.com.fiap.techchallenge.production.adapters.repository.jpa.PedidoJpaRepository;
import br.com.fiap.techchallenge.production.adapters.repository.mappers.PedidoMapper;
import br.com.fiap.techchallenge.production.core.domain.entities.enums.StatusPedidoEnum;
import br.com.fiap.techchallenge.production.core.domain.exceptions.EntityNotFoundException;
import br.com.fiap.techchallenge.production.core.dtos.MensagemPedidoProntoDTO;
import br.com.fiap.techchallenge.production.core.dtos.PedidoDTO;
import br.com.fiap.techchallenge.production.core.ports.in.pedido.PublicaPedidoInputPort;
import br.com.fiap.techchallenge.production.core.ports.out.pedido.AtualizaStatusPedidoOutputPort;
import br.com.fiap.techchallenge.production.core.ports.out.pedido.BuscaTodosPedidosOutputPort;
import br.com.fiap.techchallenge.production.core.ports.out.pedido.BuscaTodosPedidosPorStatusOutputPort;
import br.com.fiap.techchallenge.production.core.ports.out.pedido.CriaPedidoOutputPort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PedidoRepository implements CriaPedidoOutputPort, AtualizaStatusPedidoOutputPort, BuscaTodosPedidosOutputPort,
        BuscaTodosPedidosPorStatusOutputPort {

    @Value("${aws.sns.topico-producao-arn}")
    private String topicoProducaoArn;
    private final PedidoMapper pedidoMapper;
    private final PedidoJpaRepository pedidoJpaRepository;
    private final PublicaPedidoInputPort publicaPedidoInputPort;

    public PedidoRepository(PedidoMapper pedidoMapper, PedidoJpaRepository pedidoJpaRepository, PublicaPedidoInputPort publicaPedidoInputPort) {
        this.pedidoMapper = pedidoMapper;
        this.pedidoJpaRepository = pedidoJpaRepository;
        this.publicaPedidoInputPort = publicaPedidoInputPort;
    }


    @Override
    public PedidoDTO criar(PedidoDTO pedidoIn) {
        var pedido = pedidoMapper.toPedido(pedidoIn);
        var pedidoSalvo = pedidoJpaRepository.save(pedido);
        return pedidoMapper.toPedidoDTO(pedidoSalvo);
    }

    @Override
    @Transactional
    public PedidoDTO atualizarStatus(Long id, StatusPedidoEnum status) {
        var pedidoBuscado = pedidoJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido " + id + " não encontrado"));

        pedidoBuscado.setStatus(status);
        var pedido = pedidoJpaRepository.save(pedidoBuscado);
        var mensagem = new MensagemPedidoProntoDTO(pedido.getId(), pedido.getStatus());

        publicaPedidoInputPort.publicarFifo(mensagem, topicoProducaoArn);
        return pedidoMapper.toPedidoDTO(pedido);
    }

    @Override
    public List<PedidoDTO> buscarTodos() {
        var listaPedidos = pedidoJpaRepository.findAll();
        return listaPedidos.stream().map(pedidoMapper::toPedidoDTO).toList();
    }

    @Override
    public List<PedidoDTO> buscarPedidosPorStatus(StatusPedidoEnum status) {
        return pedidoJpaRepository.findByStatus(status).stream()
                .map(pedidoMapper::toPedidoDTO)
                .toList();
    }
}
