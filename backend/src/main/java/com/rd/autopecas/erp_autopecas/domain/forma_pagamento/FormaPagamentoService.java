package com.rd.autopecas.erp_autopecas.domain.forma_pagamento;

import com.rd.autopecas.erp_autopecas.domain.common.StatusCommon;
import com.rd.autopecas.erp_autopecas.domain.forma_pagamento.dto.FormaPagamentoRequest;
import com.rd.autopecas.erp_autopecas.domain.forma_pagamento.dto.FormaPagamentoResponse;
import com.rd.autopecas.erp_autopecas.domain.forma_pagamento.dto.FormaPagamentoUpdateRequest;
import com.rd.autopecas.erp_autopecas.domain.forma_pagamento.filter.FormaPagamentoFilter;
import com.rd.autopecas.erp_autopecas.domain.unidade.dto.UnidadeResponse;
import com.rd.autopecas.erp_autopecas.domain.unidade.enums.StatusUnidade;
import com.rd.autopecas.erp_autopecas.exceptions.AtributeAlredyExistsException;
import com.rd.autopecas.erp_autopecas.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class FormaPagamentoService {

    private final FormaPagamentoRepository formaPagamentoRepository;

    public FormaPagamentoResponse findById(Long id){
        FormaPagamento formaPagamento = findEntityById(id);
        return(FormaPagamentoResponse.fromEntity(formaPagamento));
    }

    public Page<FormaPagamentoResponse> findAll(FormaPagamentoFilter filter,Pageable pageable){
        StatusCommon status = null;
        if (filter.status() != null) {
            status = StatusCommon.valueOf(filter.status().toUpperCase());
        }
        String name = filter.name() == null ? "" : filter.name();
        Page<FormaPagamento> formaPagamentos = formaPagamentoRepository.findAllFormaPagamentos(name,status,pageable);
        return formaPagamentos.map(formaPagamento -> FormaPagamentoResponse.fromEntity(formaPagamento));

    }

    public FormaPagamentoResponse create(FormaPagamentoRequest formaPagamentoRequest) {
        alreadyExists(formaPagamentoRequest.name());
        FormaPagamento formaPagamento = formaPagamentoRequest.toEntity();
        formaPagamento.setStatus(StatusCommon.ATIVO);
        formaPagamentoRepository.save(formaPagamento);
        return FormaPagamentoResponse.fromEntity(formaPagamento);
    }

    public void deactivate(Long id){
        FormaPagamento formaPagamento = findEntityById(id);
        verificarStatusDesativo(formaPagamento);
        formaPagamento.setStatus(StatusCommon.DESATIVO);
        formaPagamentoRepository.save(formaPagamento);
    }

    public void active(Long id){
        FormaPagamento formaPagamento = findEntityById(id);
        verificarStatusAtivo(formaPagamento);
        formaPagamento.setStatus(StatusCommon.ATIVO);
        formaPagamentoRepository.save(formaPagamento);
    }

    @Transactional
    public FormaPagamentoResponse update(FormaPagamentoUpdateRequest updateRequest, Long id){
        FormaPagamento formaPagamento = findEntityById(id);
        if(updateRequest.name() != null){
            alreadyExists(updateRequest.name());
            formaPagamento.setName(updateRequest.name());
        }
        formaPagamentoRepository.save(formaPagamento);
        return FormaPagamentoResponse.fromEntity(formaPagamento);
    }

    //helpers
    private void alreadyExists(String name){
        if(formaPagamentoRepository.existsByName(name)){
            throw new AtributeAlredyExistsException("nome ja existente!");
        }
    }

    private void verificarStatusDesativo(FormaPagamento formaPagamento){
        if(formaPagamento.getStatus() == StatusCommon.DESATIVO){
            throw new AtributeAlredyExistsException("forma de pagamento ja desativada!");
        }
    }

    private void verificarStatusAtivo(FormaPagamento formaPagamento){
        if(formaPagamento.getStatus() == StatusCommon.ATIVO){
            throw new AtributeAlredyExistsException("forma de pagamento ja ativada!");
        }
    }
    private FormaPagamento findEntityById(Long id){
        return formaPagamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FormaPagamento não encontrado"));
    }

}
