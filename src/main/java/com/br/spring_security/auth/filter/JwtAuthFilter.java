package com.br.spring_security.auth.filter;

import com.br.spring_security.auth.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter
{
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal( @NonNull HttpServletRequest request,
                                     @NonNull HttpServletResponse response,
                                     @NonNull FilterChain filterChain) throws ServletException, IOException
    {
        logger.info("Iniciando o metodo doFilterInternal");

        String authHeader = request.getHeader("Authorization");

        logger.info("cabeçalho da autenticação: " + authHeader);

        if(!isValidAuthHeader(authHeader))
        {
            logger.info("metodo isValidAuthHeader deu false");
            filterChain.doFilter(request, response);
            return;
        }

        String token = extractToken(authHeader);

        logger.info("token extraido pelo classe JwtService " + token);

        if(token.isBlank())
        {
            logger.info("token vazio");
            filterChain.doFilter(request, response);
            return;
        }

        String username = jwtService.extractUsername(token);

        logger.info("nome do usuario " + username);

        if(username == null || username.isBlank())
        {
            logger.info("nome do usuario e nulo ou e vazio");
            filterChain.doFilter(request, response);
            return;
        }

        logger.info("carregando o usuario pelo loadUserByUsername");

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if(!jwtService.isTokenValid(token, userDetails))
        {
            logger.info("token não e valido");
            filterChain.doFilter(request, response);
            return;
        }

        logger.info("authenticando o usuario");
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());

        logger.info("usuario: " + authentication.getName() + " autenticado");
        logger.info("carregando o usuario pelo loadUserByUsername");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
        logger.info("Metodo finalizado.");
    }

    private boolean isValidAuthHeader(String authHeader)
    {
        return authHeader != null && authHeader.startsWith("Bearer ");
    }

    private String extractToken(String authHeader)
    {
        if(authHeader.length() < 7){ return "";}
        return authHeader.substring(7);
    }
}
