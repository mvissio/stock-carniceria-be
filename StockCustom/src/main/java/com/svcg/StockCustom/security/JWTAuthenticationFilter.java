package com.svcg.StockCustom.security;

import static com.svcg.StockCustom.constant.Constant.HEADER_AUTHORIZATION_KEY;
import static com.svcg.StockCustom.constant.Constant.ISSUER_INFO;
import static com.svcg.StockCustom.constant.Constant.SUPER_SECRET_KEY;
import static com.svcg.StockCustom.constant.Constant.TOKEN_BEARER_PREFIX;
import static com.svcg.StockCustom.constant.Constant.TOKEN_EXPIRATION_TIME;
import static com.svcg.StockCustom.constant.Constant.USER_ID;
import static com.svcg.StockCustom.constant.Constant.USER_ROLE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            com.svcg.StockCustom.entity.User credentials = new ObjectMapper().readValue(request.getInputStream(), com.svcg.StockCustom.entity.User.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    credentials.getUsername(), credentials.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        Object[] authorities = auth.getAuthorities().toArray();
        String token = Jwts.builder().setIssuedAt(new Date()).setIssuer(ISSUER_INFO)
                .setSubject(((User) auth.getPrincipal()).getUsername())
                .claim(USER_ID, new Long(authorities[0].toString()))
                .claim(USER_ROLE, authorities[1].toString())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SUPER_SECRET_KEY).compact();
        response.addHeader(HEADER_AUTHORIZATION_KEY, TOKEN_BEARER_PREFIX + " " + token);
    }
}
