package com.ascrud.cloud.samples.core.utils;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 *
 * @author walkman
 */
public final class JwtUtil {

    private static final String SECRET = "qazwsx123444$#%#()*&& asdaswwi1235 ?;!@#kmmmpom in***xx**&";
    private static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_AUTH = "Authorization";

    public static final String EXPIRED = "Y";
    public static final String NOT_EXPIRED = "N";

    public static final String TOKEN_F_ID = "id";
    public static final String TOKEN_F_USERNAME = "username";
    public static final String TOKEN_F_IS_EXPIRED = "isExpired";

    public static synchronized String generateToken(String userId, String username) {

        HashMap<String, Object> map = new HashMap<>();
        map.put(TOKEN_F_ID, userId);
        map.put(TOKEN_F_USERNAME, username);
        String jwt = Jwts.builder()
                .setHeaderParam(JwsHeader.KEY_ID, UUID.randomUUID().toString())
                .setSubject("user info").setClaims(map)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .compact();
        return String.format("%s %s", TOKEN_PREFIX, jwt);
    }

    public static synchronized Map<String, String> validateToken(String token) {
        HashMap<String, String> map = new HashMap<>();
        try {
            Map<String, Object> body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            String id = (String) body.get(TOKEN_F_ID);
            String username = (String) body.get(TOKEN_F_USERNAME);
            map.put(TOKEN_F_ID, id);
            map.put(TOKEN_F_USERNAME, username);
            map.put(TOKEN_F_IS_EXPIRED, NOT_EXPIRED);
        } catch (ExpiredJwtException e) {
            map.put(TOKEN_F_IS_EXPIRED, EXPIRED);
        } catch (MalformedJwtException e) {

        }
        return map;
    }
}
