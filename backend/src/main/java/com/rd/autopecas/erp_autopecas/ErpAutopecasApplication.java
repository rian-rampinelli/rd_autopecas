package com.rd.autopecas.erp_autopecas;

import com.rd.autopecas.erp_autopecas.domain.cliente.Cliente;
import com.rd.autopecas.erp_autopecas.domain.compra.Compra;
import com.rd.autopecas.erp_autopecas.domain.endereco.Endereco;
import com.rd.autopecas.erp_autopecas.domain.forma_pagamento.FormaPagamento;
import com.rd.autopecas.erp_autopecas.domain.fornecedor.Fornecedor;
import com.rd.autopecas.erp_autopecas.domain.funcionario.Funcionario;
import com.rd.autopecas.erp_autopecas.domain.funcionario.enums.CargoFuncionario;
import com.rd.autopecas.erp_autopecas.domain.funcionario.enums.StatusFuncionario;
import com.rd.autopecas.erp_autopecas.domain.user.User;
import com.rd.autopecas.erp_autopecas.domain.common.StatusTransacao;
import com.rd.autopecas.erp_autopecas.domain.venda.Venda;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class ErpAutopecasApplication {

	public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        dotenv.entries().forEach(entry ->
                System.setProperty(entry.getKey(), entry.getValue())
        );


		SpringApplication.run(ErpAutopecasApplication.class, args);{
            User user1 = new User();
            user1.setNome("rian");
            user1.setEmail("rian");
            user1.setCpf("rina");
            user1.setPassword("rian");
            System.out.println(user1);

            Funcionario funcionario1 = new Funcionario();
            funcionario1.setCargo(CargoFuncionario.ESTOQUISTA);
            funcionario1.setStatus(StatusFuncionario.ATIVO);
            funcionario1.setUser(user1);
            funcionario1.setSalario(BigDecimal.valueOf(2000.00));
            System.out.println(funcionario1);

            Cliente cliente1 = new Cliente();
            cliente1.setUser(user1);
            System.out.println(cliente1);

            Endereco endereco1 = new Endereco();
            endereco1.setRua("123 Main St");
            endereco1.setCidade("Anytown");
            endereco1.setBairro("CA");
            endereco1.setCep("12345-678");
            user1.addEndereco(endereco1);
            System.out.println(endereco1);

            Venda venda1 = new Venda();
            venda1.setTotalValue(BigDecimal.valueOf(00.00));
            venda1.setStatus(StatusTransacao.EM_ANDAMENTO);
            funcionario1.addVenda(venda1);
            cliente1.addVenda(venda1);

            FormaPagamento formaPagamento = new FormaPagamento();
            formaPagamento.setName("pix");

            venda1.setFormaPagamento(formaPagamento);
            System.out.println(venda1);

            Fornecedor fornecedor1 = new Fornecedor();
            fornecedor1.setNome("Toyota");
            fornecedor1.setCnpj("12345678901234");

            Compra compra1 = new Compra();
            compra1.setTotalValue(BigDecimal.valueOf(00.00));
            compra1.setStatus(StatusTransacao.EM_ANDAMENTO);
            fornecedor1.addCompra(compra1);
            funcionario1.addCompra(compra1);
            compra1.setFormaPagamento(formaPagamento);
            System.out.println(compra1);
        }
	}
}
