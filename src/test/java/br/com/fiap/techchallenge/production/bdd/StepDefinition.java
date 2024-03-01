package br.com.fiap.techchallenge.production.bdd;

import br.com.fiap.techchallenge.production.adapters.web.models.responses.PedidoResponse;
import br.com.fiap.techchallenge.production.core.domain.entities.enums.StatusPedidoEnum;
import br.com.fiap.techchallenge.production.utils.PedidoHelper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class StepDefinition {

    private Response response;
    private PedidoResponse pedidoResponse;

    @Dado("que um pedido já está cadastrado")
    public void pedidoJaCadastrado() {
        pedidoResponse = PedidoHelper.criaPedidoResponse();
    }

    @Quando("realizar a busca do pedido por status")
    public void realizarBuscaPedidoPorStatus() {
        String status = pedidoResponse.getStatus().name();
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/pedidos/status/{status}", status);
    }

    @Então("os pedidos devem ser exibidos com sucesso")
    public void pedidosDevemSerExibidosComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasSize(greaterThanOrEqualTo(1)))
                .body("$", everyItem(anything()));
    }

    @Quando("realizar a requisição para alterar o pedido")
    public void realizarRequisicaoParaAlterarPedido() {
        pedidoResponse.setStatus(StatusPedidoEnum.EM_PREPARACAO);
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(pedidoResponse)
                .when()
                .patch("/pedidos/{id}", pedidoResponse.getId());
    }

    @Então("o pedido deve ser alterado com sucesso")
    public void pedidoDeveSerAlteradoComSucesso() {
        response.then()
                .statusCode(HttpStatus.CREATED.value())
                .body(matchesJsonSchemaInClasspath("./schemas/PedidoResponseSchema.json"));
    }

    @Dado("que um pedido já está no status em preparação")
    public void pedidoJaNoStatusEmPreparacao() {
        realizarRequisicaoParaAlterarPedido();
    }

    @Quando("realizar a busca do pedido na fila de preparação")
    public void realizarBuscaPedidoFilaPreparacao() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/pedidos/fila-producao");
    }
}
