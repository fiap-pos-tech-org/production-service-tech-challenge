package br.com.fiap.techchallenge.production.core.dtos;

public record ClienteDTO(Long id, String nome, String cpf, String email, String telefone, EnderecoDTO endereco) {

    public ClienteDTO(String nome) {
        this(null, nome, null, null, null, null);
    }
}
