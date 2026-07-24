package com.rd.autopecas.erp_autopecas.domain.forma_pagamento;

import com.rd.autopecas.erp_autopecas.domain.forma_pagamento.dto.FormaPagamentoRequest;
import com.rd.autopecas.erp_autopecas.domain.forma_pagamento.dto.FormaPagamentoResponse;
import com.rd.autopecas.erp_autopecas.domain.forma_pagamento.dto.FormaPagamentoUpdateRequest;
import com.rd.autopecas.erp_autopecas.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FormaPagamentoService {

    private final FormaPagamentoRepository formaPagamentoRepository;

    public FormaPagamentoResponse findById(Long id){
        FormaPagamento formaPagamento = findEntityById(id);
        return(FormaPagamentoResponse.fromEntity(formaPagamento));
    }

    public List<FormaPagamentoResponse> findAll(){
        return formaPagamentoRepository.findAll().stream()
                .map(formaPagamento -> FormaPagamentoResponse.fromEntity(formaPagamento))
                .toList();
    }

    public FormaPagamentoResponse create(FormaPagamentoRequest formaPagamentoRequest) {
        FormaPagamento formaPagamento = formaPagamentoRequest.toEntity();
        formaPagamentoRepository.save(formaPagamento);
        return FormaPagamentoResponse.fromEntity(formaPagamento);
    }

    public void deleteById(Long id){
        findEntityById(id);
        formaPagamentoRepository.deleteById(id);
    }

    @Transactional
    public FormaPagamentoResponse update(FormaPagamentoUpdateRequest updateRequest, Long id){
        FormaPagamento formaPagamento = findEntityById(id);
        if(updateRequest.name() != null){
            formaPagamento.setName(updateRequest.name());
        }

        formaPagamentoRepository.save(formaPagamento);
        return FormaPagamentoResponse.fromEntity(formaPagamento);
    }

    //helpers
    public FormaPagamento findEntityById(Long id){
        return formaPagamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FormaPagamento não encontrado"));
    }

}
