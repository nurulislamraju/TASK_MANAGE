package com.phegondev.TasksApp.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {



    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService customUserDetailsService;
 /*
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
            String token = getTokenFromRequest(request);

            if (token != null){
                String username = jwtUtils.getUsernameFromToken(token);
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);


                if (jwtUtils.isTokenValid(token, userDetails)){
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }

            }
            try {
                filterChain.doFilter(request, response);
            }catch (Exception e){
                log.error(e.getMessage());
            }
    }
*/

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String token = getTokenFromRequest(request);
        log.info("Token received: {}", token);  // ADD THIS

        if (token != null){
            String username = jwtUtils.getUsernameFromToken(token);
            log.info("Username from token: {}", username);  // ADD THIS

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            log.info("UserDetails loaded: {}", userDetails);  // ADD THIS

            if (jwtUtils.isTokenValid(token, userDetails)){
                log.info("Token is VALID - setting authentication");  // ADD THIS
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } else {
                log.info("Token is INVALID");  // ADD THIS
            }
        } else {
            log.info("No token found in request");  // ADD THIS
        }

        filterChain.doFilter(request, response);
    }









    private String getTokenFromRequest(HttpServletRequest request) {
        String tokenWithBearer = request.getHeader("Authorization");
        if (tokenWithBearer != null && tokenWithBearer.startsWith("Bearer ")){
            return tokenWithBearer.substring(7);
        }
        return null;
    }




}
