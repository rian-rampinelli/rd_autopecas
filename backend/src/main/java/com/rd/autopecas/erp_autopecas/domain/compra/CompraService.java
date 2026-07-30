package com.rd.autopecas.erp_autopecas.domain.compra;

import com.rd.autopecas.erp_autopecas.domain.common.StatusTransacao;
import com.rd.autopecas.erp_autopecas.domain.compra.dto.CompraRequest;
import com.rd.autopecas.erp_autopecas.domain.compra.dto.CompraResponse;
import com.rd.autopecas.erp_autopecas.domain.forma_pagamento.FormaPagamento;
import com.rd.autopecas.erp_autopecas.domain.forma_pagamento.FormaPagamentoRepository;
import com.rd.autopecas.erp_autopecas.domain.fornecedor.Fornecedor;
import com.rd.autopecas.erp_autopecas.domain.fornecedor.FornecedorRepository;
import com.rd.autopecas.erp_autopecas.domain.funcionario.Funcionario;
import com.rd.autopecas.erp_autopecas.domain.funcionario.FuncionarioRepository;
import com.rd.autopecas.erp_autopecas.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompraService {
    
    private final CompraRepository compraRepository;
    private final FornecedorRepository fornecedorRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final FormaPagamentoRepository formaPagamentoRepository;

    public CompraResponse findById(Long id){
        Compra compra = findEntityCompra(id);
        return(CompraResponse.fromEntity(compra));
    }

    public List<CompraResponse> findTodasCompras(){
        return compraRepository.findAll().stream()
                .map(compra -> CompraResponse.fromEntity(compra))
                .toList();
    }

    @Transactional
    public CompraResponse gerarCompra(CompraRequest compraRequest) {
        Fornecedor fornecedor = findEntityFornecedor(compraRequest.idFornecedor());
        Funcionario funcionario = findEntityFuncionario(compraRequest.idFuncionario());
        FormaPagamento formaPagamento = findEntityFormaPagamento(compraRequest.idFormaPagamento());
        Compra compra = new Compra();
        compra.setFornecedor(fornecedor);
        compra.setFuncionario(funcionario);
        compra.setFormaPagamento(formaPagamento);
        compraRepository.save(compra);
        compra.setStatus(StatusTransacao.FINALIZADA);
        return CompraResponse.fromEntity(compra);
    }


    //helpers
    public Compra findEntityCompra(Long id){
        return compraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("compra não encontrado"));
    }

    public Fornecedor findEntityFornecedor(Long id){
        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("fornecedor não encontrado"));
    }

    public Funcionario findEntityFuncionario(Long id){
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("funcionario não encontrado"));
    }


    public FormaPagamento findEntityFormaPagamento(Long id){
        return formaPagamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FormaPagamento não encontrada"));
    }

}
