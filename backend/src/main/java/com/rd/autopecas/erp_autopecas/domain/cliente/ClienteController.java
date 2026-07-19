package com.rd.autopecas.erp_autopecas.domain.cliente;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;
}
