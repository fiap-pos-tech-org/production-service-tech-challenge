package br.com.fiap.techchallenge.production.utils;

import br.com.fiap.techchallenge.production.adapters.repository.models.Cliente;
import br.com.fiap.techchallenge.production.core.dtos.ClienteDTO;

public class ClienteHelper {

    public static ClienteDTO criaClienteDTO() {
        return new ClienteDTO(1L, "cliente1", "56312729036", "cliente1@email.com");
    }

    public static Cliente criaCliente() {
        return new Cliente("cliente", "80313100098", "cliente@email.com");
    }

    public static Cliente criaCopiaClienteDTO() {
        ClienteDTO clienteDTO = criaClienteDTO();
        return new Cliente(
                clienteDTO.nome(),
                clienteDTO.cpf(),
                clienteDTO.email()
        );
    }
}
