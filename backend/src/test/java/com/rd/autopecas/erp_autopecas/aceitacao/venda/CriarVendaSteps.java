package com.rd.autopecas.erp_autopecas.aceitacao.venda;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CriarVendaSteps {

    private String cargo;
    private String statusVenda;

    @Given("que estou autenticado como {string}")
    public void queOFuncionarioEstejaVendendo(String cargoCenario) {
        cargo = cargoCenario;
    }

    @When("o sistema criar a venda")
    public void oSistemaCriarAVenda() {
        if (cargo.equals("vendedor") || cargo.equals("gerente") || cargo.equals("admin")) {
            this.statusVenda = "criada";
        } else {
            this.statusVenda = "recusada";
        }
    }

    @Then("a venda deve ser {string}")
    public void aVendaDeveSer(String statusVendaCenario) {
        assertEquals(statusVendaCenario, statusVenda);
    }
}