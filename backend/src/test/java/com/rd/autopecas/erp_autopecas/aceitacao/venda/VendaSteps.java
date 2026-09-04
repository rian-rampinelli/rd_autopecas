package com.rd.autopecas.erp_autopecas.aceitacao.venda;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VendaSteps {

    private String permissao;
    private String statusVenda;
    private BigDecimal qtdEstoque;
    private BigDecimal qtdAdicionada;

    @Given("tento adicionar item a venda {string}")
    public void queTentoAdicionarItem(String statusVendaCenario) {
        this.statusVenda = statusVendaCenario;
    }

    @Given("item no estoque seja de {double} e item adicionado seja de {double}")
    public void queTentoAdicionarQuantidadeItem(Double qtdEstoqueCenario, Double qtdAdicionadaCenario) {
        this.qtdEstoque = BigDecimal.valueOf(qtdEstoqueCenario);
        this.qtdAdicionada = BigDecimal.valueOf(qtdAdicionadaCenario);
    }

    @When("o funcionário tentar adicionar o item à venda")
    public void oFuncionarioAdicionarItem() {
        if (!statusVenda.equals("em_andamento")) {
            this.permissao = "recusado";
        } else if (qtdAdicionada != null && qtdEstoque != null && qtdAdicionada.compareTo(qtdEstoque) > 0) {
            this.permissao = "recusado";
        } else {
            this.permissao = "adicionado";
        }
    }

    @Then("o item deve ser {string}")
    public void oItemDeveSer(String permissaoCenario) {
        assertEquals(permissaoCenario, permissao);
    }
}