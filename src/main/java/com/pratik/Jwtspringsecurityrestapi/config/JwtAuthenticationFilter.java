package com.pratik.Jwtspringsecurityrestapi.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");//Get the JWT Token from the Header
        final String jwt;
        final String userEmail;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            //Extract the Token from the Header
            jwt = authHeader.substring(7);
            //Extract the user email from the token

            userEmail = jwtService.extractUserEmail(jwt);
            //continue the filter
            filterChain.doFilter(request, response);
            return;

        }else{
            //return 403 Forbidden
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
        }
    }
}
