package com.rd.autopecas.erp_autopecas.security;

import com.rd.autopecas.erp_autopecas.domain.funcionario.FuncionarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final FuncionarioRepository funcionarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        if(StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")){
            //validartoken
            String token = authorizationHeader.substring(7);
            if(tokenProvider.isTokenValid(token)){
                String userName = tokenProvider.getUsername(token);
                //pegar do db pelo email e instanciar em um userdetails
                UserDetails userDetails = funcionarioRepository.findByUser_Email(userName)
                        .orElseThrow(() -> new UsernameNotFoundException("nao encontrado"));
                //confirma q o user ta autenticado a partir de agora
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }


        }
        filterChain.doFilter(request,response);

    }
}
