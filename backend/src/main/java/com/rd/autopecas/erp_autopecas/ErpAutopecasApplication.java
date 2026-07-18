package com.rd.autopecas.erp_autopecas;

import com.rd.autopecas.erp_autopecas.domain.Item.Item;
import com.rd.autopecas.erp_autopecas.domain.carro.Carro;
import com.rd.autopecas.erp_autopecas.domain.cliente.Cliente;
import com.rd.autopecas.erp_autopecas.domain.compra.Compra;
import com.rd.autopecas.erp_autopecas.domain.endereco.Endereco;
import com.rd.autopecas.erp_autopecas.domain.estoque.Estoque;
import com.rd.autopecas.erp_autopecas.domain.forma_pagamento.FormaPagamento;
import com.rd.autopecas.erp_autopecas.domain.fornecedor.Fornecedor;
import com.rd.autopecas.erp_autopecas.domain.funcionario.Funcionario;
import com.rd.autopecas.erp_autopecas.domain.role.RoleFuncionario;
import com.rd.autopecas.erp_autopecas.domain.funcionario.enums.StatusFuncionario;
import com.rd.autopecas.erp_autopecas.domain.item_compra.ItemCompra;
import com.rd.autopecas.erp_autopecas.domain.item_estoque.ItemEstoque;
import com.rd.autopecas.erp_autopecas.domain.item_venda.ItemVenda;
import com.rd.autopecas.erp_autopecas.domain.movimentacao_estoque.MovimentacaoEstoque;
import com.rd.autopecas.erp_autopecas.domain.movimentacao_estoque.TypeMovimentacao;
import com.rd.autopecas.erp_autopecas.domain.role.Role;
import com.rd.autopecas.erp_autopecas.domain.unidade.StatusUnidade;
import com.rd.autopecas.erp_autopecas.domain.unidade.Unidade;
import com.rd.autopecas.erp_autopecas.domain.user.User;
import com.rd.autopecas.erp_autopecas.domain.common.StatusTransacao;
import com.rd.autopecas.erp_autopecas.domain.venda.Venda;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class ErpAutopecasApplication {

	public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        dotenv.entries().forEach(entry ->
                System.setProperty(entry.getKey(), entry.getValue())
        );


		SpringApplication.run(ErpAutopecasApplication.class, args);{
            System.out.println("hello");
        }
	}
}
