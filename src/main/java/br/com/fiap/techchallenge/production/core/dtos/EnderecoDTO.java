package br.com.fiap.techchallenge.production.core.dtos;

public record EnderecoDTO(Long id, String logradouro, String rua, Integer numero, String bairro, String cidade,
                          String estado) {

}