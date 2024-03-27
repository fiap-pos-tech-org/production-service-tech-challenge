package br.com.fiap.techchallenge.production.config;

import br.com.fiap.techchallenge.production.core.ports.in.pedido.*;
import br.com.fiap.techchallenge.production.core.ports.out.pedido.*;
import br.com.fiap.techchallenge.production.core.usecases.pedido.*;
import com.squareup.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreInjectionConfig {

    @Bean
    CriaPedidoInputPort criarPedido(CriaPedidoOutputPort criaPedidoOutputPort) {
        return new CriaPedidoUseCase(criaPedidoOutputPort);
    }

    @Bean
    AtualizaStatusPedidoInputPort atualizaStatusPedido(AtualizaStatusPedidoOutputPort atualizaStatusPedidoOutputPort) {
        return new AtualizaStatusPedidoUseCase(atualizaStatusPedidoOutputPort);
    }

    @Bean
    BuscaPedidosOrdenadosPorPrioridadeInputPort ordenaPorPrioridade(BuscaTodosPedidosOutputPort buscaTodosPedidosOutputPort) {
        return new BuscaPedidosPorPrioridadeUseCase(buscaTodosPedidosOutputPort);
    }

    @Bean
    BuscaTodosPedidosPorStatusInputPort buscarPorStatus(BuscaTodosPedidosPorStatusOutputPort buscaTodosPedidosOutputPort) {
        return new BuscaTodosPedidosPorStatusUseCase(buscaTodosPedidosOutputPort);
    }

    @Bean
    PublicaPedidoInputPort publicaPedido(PublicaPedidoOutputPort publicaPedidoOutputPort) {
        return new PublicaPedidoUseCase(publicaPedidoOutputPort);
    }

    @Bean
    OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }
}
