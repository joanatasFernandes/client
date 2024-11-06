package com.clientapi.security;


import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

@Component

public class JwtRequestFilter implements Filter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        final String authorizationHeader = httpRequest.getHeader("Authorization");

        String username = null;
        String jwt = null;

        // Verifica se o cabeçalho Authorization está presente e se começa com "Bearer "
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7); // Extrai o token JWT
            username = jwtUtil.extractUsername(jwt); // Extrai o nome de usuário do token
        }

        // Se o contexto de segurança não estiver definido, verifica o token e define a autenticação
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username); // Carrega os detalhes do usuário
            if (jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken); // Define a autenticação no contexto
            }
        }

        chain.doFilter(request, response); // Continua a cadeia de filtros
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Implementação padrão; pode ser deixada vazia
    }

    @Override
    public void destroy() {
        // Implementação padrão; pode ser deixada vazia
    }
}
