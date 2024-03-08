package br.com.fiap.techchallenge.production.utils;

import br.com.fiap.techchallenge.production.core.dtos.EnderecoDTO;

public class EnderecoHelper {

    private EnderecoHelper() {
    }

    public static EnderecoDTO criaEnderecoDTO() {
        return new EnderecoDTO(1L, "Avenida", "Brasil", 1500, "Centro",
                "Uberlândia", "MG");
    }

}