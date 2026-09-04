package com.rd.autopecas.erp_autopecas.domain.venda;

import com.rd.autopecas.erp_autopecas.domain.Item.Item;
import com.rd.autopecas.erp_autopecas.domain.Item.ItemRepository;
import com.rd.autopecas.erp_autopecas.domain.cliente.Cliente;
import com.rd.autopecas.erp_autopecas.domain.cliente.ClienteRepository;
import com.rd.autopecas.erp_autopecas.domain.common.StatusTransacao;

import com.rd.autopecas.erp_autopecas.domain.item_compra.ItemCompra;
import com.rd.autopecas.erp_autopecas.domain.venda.dto.VendaRequest;
import com.rd.autopecas.erp_autopecas.domain.venda.dto.VendaResponse;
import com.rd.autopecas.erp_autopecas.domain.estoque.Estoque;
import com.rd.autopecas.erp_autopecas.domain.estoque.EstoqueRepository;
import com.rd.autopecas.erp_autopecas.domain.estoque.EstoqueService;
import com.rd.autopecas.erp_autopecas.domain.forma_pagamento.FormaPagamento;
import com.rd.autopecas.erp_autopecas.domain.forma_pagamento.FormaPagamentoRepository;
import com.rd.autopecas.erp_autopecas.domain.funcionario.Funcionario;
import com.rd.autopecas.erp_autopecas.domain.funcionario.FuncionarioRepository;
import com.rd.autopecas.erp_autopecas.domain.item_venda.ItemVenda;
import com.rd.autopecas.erp_autopecas.domain.item_venda.ItemVendaRepository;
import com.rd.autopecas.erp_autopecas.domain.item_venda.dto.ItemVendaRequest;
import com.rd.autopecas.erp_autopecas.exceptions.ResourceNotFoundException;
import com.rd.autopecas.erp_autopecas.exceptions.ValidationException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class VendaService {
    
    private final VendaRepository vendaRepository;
    private final ClienteRepository clienteRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final FormaPagamentoRepository formaPagamentoRepository;
    private final ItemRepository itemRepository;
    private final ItemVendaRepository itemVendaRepository;
    private final EstoqueService estoqueService;
    private final EstoqueRepository estoqueRepository;


    public VendaResponse findById(Long id){
        Venda venda = findEntityVenda(id);
        return(VendaResponse.fromEntity(venda));
    }

    public List<VendaResponse> findTodasVendas(){
        return vendaRepository.findAll().stream()
                .map(venda -> VendaResponse.fromEntity(venda))
                .toList();
    }

    @Transactional
    public VendaResponse gerarVenda(VendaRequest vendaRequest) {
        Cliente cliente = findEntityCliente(vendaRequest.idCliente());
        Funcionario funcionario = findEntityFuncionario(vendaRequest.idFuncionario());
        funcionario.validarAtivo();
        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setFuncionario(funcionario);
        venda.setStatus(StatusTransacao.EM_ANDAMENTO);
        vendaRepository.save(venda);
        return VendaResponse.fromEntity(venda);
    }

    @Transactional
    public VendaResponse adicionarItemNaVenda(Long idVenda, ItemVendaRequest request){
        Venda venda = findEntityVenda(idVenda);
        verificaTransaçãoEmAndamento(venda);
        ItemVenda itemVenda = findEntityItemVendaByItemAndVenda(request.idItem(),idVenda);
        if(itemVenda == null){
            itemVenda = new ItemVenda();
            Item item = findEntityItem(request.idItem());
            itemVenda.setQuantidade(request.quantidade());
            itemVenda.setItemValue(request.itemValue());
            itemVenda.setItem(item);
            venda.addItemVenda(itemVenda);
        }
        else{
            itemVenda.setQuantidade(itemVenda.getQuantidade().add(request.quantidade()));
        }
        recalcularTotal(venda);
        vendaRepository.save(venda);
        return VendaResponse.fromEntity(venda);
    }

    @Transactional
    public VendaResponse removerItemDaVenda(Long idVenda,Long idItemVenda){
        Venda venda = findEntityVenda(idVenda);
        verificaTransaçãoEmAndamento(venda);
        ItemVenda itemVenda = findEntityItemVendaInVenda(idItemVenda,idVenda);
        venda.removeItemVenda(itemVenda);
        recalcularTotal(venda);
        vendaRepository.save(venda);
        return VendaResponse.fromEntity(venda);
    }

    @Transactional
    public VendaResponse finalizarVenda(Long idVenda){
        Venda venda = findEntityVenda(idVenda);
        verificaTransaçãoPaga(venda);
        venda.setStatus(StatusTransacao.FINALIZADA);
        vendaRepository.save(venda);
        return VendaResponse.fromEntity(venda);
    }

    @Transactional
    public VendaResponse processarPagamento(Long idVenda,Long idFormaDePagamento){
        Venda venda = findEntityVenda(idVenda);
        FormaPagamento formaPagamento = findEntityFormaPagamento(idFormaDePagamento);
        verificaTransaçãoEmAndamento(venda);
        venda.setStatus(StatusTransacao.AGUARDANDO_PAGAMENTO);
        log.info("pagamento foi aprovado");
        venda.setStatus(StatusTransacao.PAGA);
        venda.setFormaPagamento(formaPagamento);
        vendaRepository.save(venda);
        return VendaResponse.fromEntity(venda);
    }

    @Transactional
    public VendaResponse registrarEntrega(Long idVenda,Long idEstoque){
        log.info("entrei na entrega");
        Venda venda = findEntityVenda(idVenda);
        Estoque estoque = findEntityEstoque(idEstoque);
        verificaTransaçãoFinalizada(venda);
        registrarEntradaNoEstoque(venda,estoque);
        venda.setStatus(StatusTransacao.ENTREGUE);
        vendaRepository.save(venda);
        return VendaResponse.fromEntity(venda);
    }

    public VendaResponse registrarCancelamento(Long idVenda){
        Venda venda = findEntityVenda(idVenda);
        verificaTransaçãoFinalizada(venda);
        venda.setStatus(StatusTransacao.CANCELADA);
        vendaRepository.save(venda);
        return VendaResponse.fromEntity(venda);
    }


    public VendaResponse registrarAbandono(Long idVenda){
        log.info("entrei na abandono");
        Venda venda = findEntityVenda(idVenda);
        verificaTransaçãoEmAndamento(venda);
        venda.setStatus(StatusTransacao.ABANDONADA);
        vendaRepository.save(venda);
        return VendaResponse.fromEntity(venda);
    }



    private void recalcularTotal(Venda venda){
        BigDecimal totalValue = venda.calcularTotal();
        venda.setTotalValue(totalValue);
    }

    private void registrarEntradaNoEstoque(Venda venda,Estoque estoque){
        for(ItemVenda itemVenda : venda.getItemsVenda()){
            estoqueService.registrarEntrada(estoque,itemVenda.getItem().getId(),itemVenda.getQuantidade(),"n sei ainda como");
        }
    }




    //helpers
    private Venda findEntityVenda(Long id){
        return vendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("venda não encontrado!"));
    }

    private Estoque findEntityEstoque(Long id){
        return estoqueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("estoque não encontrado!"));
    }

    private Cliente findEntityCliente(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("cliente não encontrado!"));
    }

    private Funcionario findEntityFuncionario(Long id){
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("funcionario não encontrado!"));
    }

    private Item findEntityItem(Long id){
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("item não encontrado!"));
    }


    private FormaPagamento findEntityFormaPagamento(Long id){
        return formaPagamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FormaPagamento não encontrada"));
    }

    private ItemVenda findEntityItemVendaByItemAndVenda(Long idItem, Long idVenda){
        return itemVendaRepository.findByItem_IdAndVenda_Id(idItem, idVenda)
                .orElse(null);
    }

    private ItemVenda findEntityItemVendaInVenda(Long idItemVenda,Long idVenda){
        return itemVendaRepository.findByIdAndVenda_Id(idItemVenda, idVenda)
                .orElseThrow(() -> new ResourceNotFoundException("Item não pertence à essa venda!."));
    }

    private void verificaTransaçãoEmAndamento(Venda venda){
        if(venda.getStatus() != StatusTransacao.EM_ANDAMENTO){
            throw new ValidationException("Transação não esta em andamento!");
        }
    }

    private void verificaTransaçãoPaga(Venda venda){
        if(venda.getStatus() != StatusTransacao.PAGA){
            throw new ValidationException("Transação precisa ser paga!");
        }
    }


    private void verificaTransaçãoFinalizada(Venda venda){
        if(venda.getStatus() != StatusTransacao.FINALIZADA){
            throw new ValidationException("Transação precisa estar finalizada!");
        }
    }


}
