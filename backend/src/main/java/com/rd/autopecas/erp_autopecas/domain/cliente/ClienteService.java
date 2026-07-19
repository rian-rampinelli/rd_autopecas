package com.rd.autopecas.erp_autopecas.domain.cliente;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
}
