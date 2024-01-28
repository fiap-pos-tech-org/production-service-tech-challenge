package br.com.fiap.techchallenge.production.config;

import br.com.fiap.techchallenge.production.core.ports.in.pedido.*;
import br.com.fiap.techchallenge.production.core.ports.out.pedido.AtualizaStatusPedidoOutputPort;
import br.com.fiap.techchallenge.production.core.ports.out.pedido.BuscaTodosPedidosOutputPort;
import br.com.fiap.techchallenge.production.core.ports.out.pedido.BuscaTodosPedidosPorStatusOutputPort;
import br.com.fiap.techchallenge.production.core.ports.out.pedido.BuscarPedidoPorIdOutputPort;
import br.com.fiap.techchallenge.production.core.usecases.pedido.AtualizaStatusPedidoUseCase;
import br.com.fiap.techchallenge.production.core.usecases.pedido.BuscaPedidosPorPrioridadeUseCase;
import br.com.fiap.techchallenge.production.core.usecases.pedido.BuscaTodosPedidosPorStatusUseCase;
import br.com.fiap.techchallenge.production.core.usecases.pedido.BuscaTodosPedidosUseCase;
import com.squareup.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreInjectionConfig {

    @Bean
    AtualizaStatusPedidoInputPort atualizaStatusPedido(AtualizaStatusPedidoOutputPort atualizaStatusPedidoOutputPort){
        return new AtualizaStatusPedidoUseCase(atualizaStatusPedidoOutputPort);
    }

    @Bean
    BuscaTodosPedidosInputPort buscarTodosPedidos(BuscaTodosPedidosOutputPort buscaTodosPedidosOutputPort) {
        return new BuscaTodosPedidosUseCase(buscaTodosPedidosOutputPort);
    }

    @Bean
    BuscaTodosPedidosPorStatusInputPort buscarPorStatus(BuscaTodosPedidosPorStatusOutputPort buscaTodosPedidosOutputPort) {
        return new BuscaTodosPedidosPorStatusUseCase(buscaTodosPedidosOutputPort);
    }

    @Bean
    BuscaPedidosOrdenadosPorPrioridadeInputPort ordenaPorPrioridade(BuscaTodosPedidosOutputPort buscaTodosPedidosOutputPort) {
        return new BuscaPedidosPorPrioridadeUseCase(buscaTodosPedidosOutputPort);
    }

    @Bean
    OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }
}
