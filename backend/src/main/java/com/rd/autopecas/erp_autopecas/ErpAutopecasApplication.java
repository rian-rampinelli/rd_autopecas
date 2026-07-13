package com.rd.autopecas.erp_autopecas;

import com.rd.autopecas.erp_autopecas.domain.user.User;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ErpAutopecasApplication {

	public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        dotenv.entries().forEach(entry ->
                System.setProperty(entry.getKey(), entry.getValue())
        );


		SpringApplication.run(ErpAutopecasApplication.class, args);{
            User user1 = new User();
            user1.setNome("rian");
            user1.setEmail("rian");
            user1.setCpf("rina");
            user1.setPassword("rian");
            System.out.println(user1);
        }

	}
}
