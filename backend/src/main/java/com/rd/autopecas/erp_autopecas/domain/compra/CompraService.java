package com.rd.autopecas.erp_autopecas.domain.compra;

import com.rd.autopecas.erp_autopecas.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompraService {
    
    private final CompraRepository compraRepository;

   /* public CompraResponse findById(Long id){
        Compra compra = findEntityCompra(id);
        return(CompraResponse.fromEntity(compra));
    }

    public List<CompraResponse> findAll(){
        return compraRepository.findComprasWithEstoques().stream()
                .map(compra -> CompraResponse.fromEntity(compra))
                .toList();
    }

    @Transactional
    public CompraResponse gerarCompra(CompraRequest compraRequest) {
        Compra compra = compraRequest.toEntity();

        compraRepository.save(compra);
        return CompraResponse.fromEntity(compra);
    }*/


    //helpers
    public Compra findEntityCompra(Long id){
        return compraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("compra não encontrado"));
    }
    


}
