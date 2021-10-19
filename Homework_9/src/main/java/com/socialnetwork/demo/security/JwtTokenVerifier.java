package com.socialnetwork.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JwtTokenVerifier extends OncePerRequestFilter {

    private final UserDetailsService personService;
    private final JwtConfig jwtConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = authorizationHeader.replace("Bearer ", "");
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(jwtConfig.getSecretKey())
                    .parseClaimsJws(token);

            String username = claimsJws.getBody().getSubject();

            UserDetails userDetails = personService.loadUserByUsername(username);
            if (!userDetails.isAccountNonExpired() ||
                    !userDetails.isAccountNonLocked() ||
                    !userDetails.isEnabled() ||
                    !userDetails.isCredentialsNonExpired()) {
                throw new IllegalStateException("Token cannot be trusted");
            }

            var authorities = (List<Map<String, String>>) claimsJws.getBody().get("Authorities");
            List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
            if (authorities != null) {
                grantedAuthorityList = authorities.stream()
                        .map(auth -> new SimpleGrantedAuthority("ROLE_" + auth.get("authority")))
                        .collect(Collectors.toList());
            }

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    grantedAuthorityList
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException e) {
            throw new IllegalStateException("Token cannot be trusted");
        }

        filterChain.doFilter(request, response);
    }
}