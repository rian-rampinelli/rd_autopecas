package com.rd.autopecas.erp_autopecas.domain.auth;

import com.rd.autopecas.erp_autopecas.domain.auth.dto.LoginRequest;
import com.rd.autopecas.erp_autopecas.domain.auth.dto.LoginResponse;
import com.rd.autopecas.erp_autopecas.domain.auth.dto.RegisterRequest;
import com.rd.autopecas.erp_autopecas.domain.auth.dto.RegisterResponse;
import com.rd.autopecas.erp_autopecas.domain.funcionario.Funcionario;
import com.rd.autopecas.erp_autopecas.domain.funcionario.FuncionarioRepository;
import com.rd.autopecas.erp_autopecas.domain.funcionario.enums.StatusFuncionario;
import com.rd.autopecas.erp_autopecas.domain.role.Role;
import com.rd.autopecas.erp_autopecas.domain.role.RoleRepository;
import com.rd.autopecas.erp_autopecas.domain.user.User;
import com.rd.autopecas.erp_autopecas.domain.user.UserRepository;
import com.rd.autopecas.erp_autopecas.exceptions.AtributeAlredyExistsException;
import com.rd.autopecas.erp_autopecas.exceptions.ValidationException;
import com.rd.autopecas.erp_autopecas.security.TokenProvider;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@AllArgsConstructor
public class AuthService {

    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final FuncionarioRepository funcionarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    public RegisterResponse register(RegisterRequest registerRequest){
        if(funcionarioRepository.existsByUser_Email(registerRequest.email())){
            throw new AtributeAlredyExistsException("Email já cadastrado!");
        }

        User user = new User();
        user.setNome(registerRequest.userName());
        user.setEmail(registerRequest.email());
        user.setCpf(registerRequest.cpf());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));

        Set<Role> roles = new HashSet<>(roleRepository.findAllById(registerRequest.roleIds()));

        if (roles.size() != registerRequest.roleIds().size()) {
            throw new ValidationException("Uma ou mais roles não existem");
        }

        Funcionario funcionario = new Funcionario();
        funcionario.setUser(user);
        funcionario.setStatus(StatusFuncionario.ATIVO);
        funcionario.setSalario(registerRequest.salary());
        funcionario.setRoles(roles);

        userRepository.save(user);
        funcionarioRepository.save(funcionario);

        return RegisterResponse.fromEntity(user,funcionario);
    }

    public LoginResponse login(LoginRequest loginRequest) throws Exception{
        //authentication provider -> userDetailsService -> PassWordEncoder.matches -> autenticado!
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email(),loginRequest.password()));
        String token = tokenProvider.gerarToken(authentication);

        return new LoginResponse(token, tokenProvider.expiresIn(token));
    }
}
