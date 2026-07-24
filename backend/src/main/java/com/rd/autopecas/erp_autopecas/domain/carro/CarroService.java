package com.rd.autopecas.erp_autopecas.domain.carro;

import com.rd.autopecas.erp_autopecas.domain.carro.dto.CarroRequest;
import com.rd.autopecas.erp_autopecas.domain.carro.dto.CarroResponse;
import com.rd.autopecas.erp_autopecas.domain.carro.dto.CarroUpdateRequest;
import com.rd.autopecas.erp_autopecas.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarroService {

    private final CarroRepository carroRepository;

    public CarroResponse findById(Long id){
        Carro carro = findEntityById(id);
        return(CarroResponse.fromEntity(carro));
    }

    public List<CarroResponse> findAll(){
        return carroRepository.findAll().stream()
                .map(carro -> CarroResponse.fromEntity(carro))
                .toList();
    }

    public CarroResponse create(CarroRequest carroRequest) {
        Carro carro = carroRequest.toEntity();
        carroRepository.save(carro);
        return CarroResponse.fromEntity(carro);
    }

    public void deleteById(Long id){
        findEntityById(id);
        carroRepository.deleteById(id);
    }

    @Transactional
    public CarroResponse update(CarroUpdateRequest updateRequest, Long id){
        Carro carro = findEntityById(id);

        if(updateRequest.name() != null){
            carro.setNome(updateRequest.name());
        }
        if(updateRequest.motor() != null){
            carro.setMotor(updateRequest.motor());
        }
        if(updateRequest.marca() != null){
            carro.setMarca(updateRequest.marca());
        }
        if(updateRequest.modelo() != null){
            carro.setModelo(updateRequest.modelo());
        }
        if(updateRequest.versao() != null){
            carro.setVersao(updateRequest.versao());
        }
        carroRepository.save(carro);
        return CarroResponse.fromEntity(carro);
    }

    //helpers
    public Carro findEntityById(Long id){
        return carroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Carro não encontrado"));
    }

   
}
