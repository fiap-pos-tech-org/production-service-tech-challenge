package br.com.fiap.techchallenge.production.adapters.repository.jpa;

import br.com.fiap.techchallenge.production.adapters.repository.models.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPedidoJpaRepository extends JpaRepository<ItemPedido, Long> {

    List<ItemPedido> findAllByPedidoId(Long pedidoid);
}
