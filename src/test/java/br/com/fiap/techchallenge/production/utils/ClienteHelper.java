package br.com.fiap.techchallenge.production.utils;

import br.com.fiap.techchallenge.production.core.dtos.ClienteDTO;

public class ClienteHelper {

    public static ClienteDTO criaClienteDTO() {
        return new ClienteDTO(1L, "cliente1", "56312729036", "cliente1@email.com",
                "(34) 99988-7766", EnderecoHelper.criaEnderecoDTO());
    }
}
