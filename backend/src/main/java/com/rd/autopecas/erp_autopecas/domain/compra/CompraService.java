package com.rd.autopecas.erp_autopecas.domain.compra;

import com.rd.autopecas.erp_autopecas.domain.Item.Item;
import com.rd.autopecas.erp_autopecas.domain.Item.ItemRepository;
import com.rd.autopecas.erp_autopecas.domain.common.StatusTransacao;
import com.rd.autopecas.erp_autopecas.domain.compra.dto.CompraRequest;
import com.rd.autopecas.erp_autopecas.domain.compra.dto.CompraResponse;
import com.rd.autopecas.erp_autopecas.domain.estoque.Estoque;
import com.rd.autopecas.erp_autopecas.domain.estoque.EstoqueRepository;
import com.rd.autopecas.erp_autopecas.domain.estoque.EstoqueService;
import com.rd.autopecas.erp_autopecas.domain.forma_pagamento.FormaPagamento;
import com.rd.autopecas.erp_autopecas.domain.forma_pagamento.FormaPagamentoRepository;
import com.rd.autopecas.erp_autopecas.domain.fornecedor.Fornecedor;
import com.rd.autopecas.erp_autopecas.domain.fornecedor.FornecedorRepository;
import com.rd.autopecas.erp_autopecas.domain.funcionario.Funcionario;
import com.rd.autopecas.erp_autopecas.domain.funcionario.FuncionarioRepository;
import com.rd.autopecas.erp_autopecas.domain.item_compra.ItemCompra;
import com.rd.autopecas.erp_autopecas.domain.item_compra.ItemCompraRepository;
import com.rd.autopecas.erp_autopecas.domain.item_compra.dto.ItemCompraRequest;
import com.rd.autopecas.erp_autopecas.exceptions.ResourceNotFoundException;
import com.rd.autopecas.erp_autopecas.exceptions.ValidationException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class CompraService {
    
    private final CompraRepository compraRepository;
    private final FornecedorRepository fornecedorRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final FormaPagamentoRepository formaPagamentoRepository;
    private final ItemRepository itemRepository;
    private final ItemCompraRepository itemCompraRepository;

    private final EstoqueService estoqueService;
    private final EstoqueRepository estoqueRepository;

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
        compra.setStatus(StatusTransacao.EM_ANDAMENTO);
        compraRepository.save(compra);
        return CompraResponse.fromEntity(compra);
    }

    @Transactional
    public CompraResponse adicionarItemNaCompra(Long idCompra, ItemCompraRequest request){
        Compra compra = findEntityCompra(idCompra);
        verificaTransaçãoPermitida(compra);
        ItemCompra itemCompra = findEntityItemCompraByItemAndCompra(request.idItem(),idCompra);
        if(itemCompra == null){
            itemCompra = new ItemCompra();
            Item item = findEntityItem(request.idItem());
            itemCompra.setQuantidade(request.quantidade());
            itemCompra.setItemValue(request.itemValue());
            itemCompra.setItem(item);
            compra.addItemCompra(itemCompra);
        }
        else{
            itemCompra.setQuantidade(itemCompra.getQuantidade().add(request.quantidade()));
        }
        compraRepository.save(compra);
        itemCompraRepository.save(itemCompra);
        return CompraResponse.fromEntity(compra);
    }

    @Transactional
    public CompraResponse removerItemDaCompra(Long idCompra,Long idItemCompra){
        Compra compra = findEntityCompra(idCompra);
        verificaTransaçãoPermitida(compra);
        ItemCompra itemCompra = findEntityItemCompraInCompra(idItemCompra,idCompra);
        compra.removeItemCompra(itemCompra);
        compraRepository.save(compra);
        return CompraResponse.fromEntity(compra);
    }

    @Transactional
    public CompraResponse finalizarCompra(Long idEstoque,Long idCompra){
        Compra compra = findEntityCompra(idCompra);
        Estoque estoque = findEntityEstoque(idEstoque);
        verificaTransaçãoPermitida(compra);
        for(ItemCompra itemCompra : compra.getItemsCompra()){
            estoqueService.adicionarItem(estoque,itemCompra.getItem().getId(),itemCompra.getQuantidade(),"n sei ainda como");
        }
        BigDecimal totalValue = compra.calcularTotal();
        compra.setStatus(StatusTransacao.FINALIZADA);
        compra.setTotalValue(totalValue);
        compraRepository.save(compra);
        return CompraResponse.fromEntity(compra);
    }


    //helpers
    private Compra findEntityCompra(Long id){
        return compraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("compra não encontrado"));
    }

    private Estoque findEntityEstoque(Long id){
        return estoqueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("estoque não encontrado"));
    }

    private Fornecedor findEntityFornecedor(Long id){
        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("fornecedor não encontrado"));
    }

    private Funcionario findEntityFuncionario(Long id){
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("funcionario não encontrado"));
    }

    private Item findEntityItem(Long id){
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("item não encontrado"));
    }

    private ItemCompra findEntityItemCompraInCompra(Long idItemCompra,Long idCompra){
        return itemCompraRepository.findByIdAndCompra_Id(idItemCompra, idCompra)
                .orElseThrow(() -> new ResourceNotFoundException("Item não pertence à compra."));
    }

    private ItemCompra findEntityItemCompraByItemAndCompra(Long idItem,Long idCompra){
        return itemCompraRepository.findByItem_IdAndCompra_Id(idItem, idCompra)
                .orElse(null);
    }


    private ItemCompra findEntityItemCompra(Long id){
        return itemCompraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("item na compra não encontrado"));
    }

    private FormaPagamento findEntityFormaPagamento(Long id){
        return formaPagamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FormaPagamento não encontrada"));
    }

    private void verificaTransaçãoPermitida(Compra compra){
        if(compra.getStatus() != StatusTransacao.EM_ANDAMENTO){
            throw new ValidationException("Transação ja finalizada");
        }
    }

}
