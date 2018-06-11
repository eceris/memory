package com.eceris.memory.authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public enum TokenAuthenticationService {
    INSTANCE;

    private static final long EXPIRATION_TIME = 864_000_000; //10days
    private static final String SECRET_KEY = "ThisIsASecret";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_NAME = "Authorization";

    public static void addAuthentication(HttpServletResponse response, @RequestParam String username) {
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(EXPIRATION_TIME)))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
        response.addHeader(HEADER_NAME, TOKEN_PREFIX + JWT);
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_NAME);
        if (token != null) {
            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            return user != null ? new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) : null;
        }
        return null;
    }
}
