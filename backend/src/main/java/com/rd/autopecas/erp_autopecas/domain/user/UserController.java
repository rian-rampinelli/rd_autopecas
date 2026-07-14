package com.rd.autopecas.erp_autopecas.domain.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping
    private String teste(){
        return "hi";
    }

}
