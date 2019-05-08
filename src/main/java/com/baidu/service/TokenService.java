package com.baidu.service;

import java.io.IOException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.baidu.utils.KeyUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService implements ITokenService {

    @Override
    public String getToken(String service, String scope) throws Exception {
        PrivateKey p = KeyUtils.getPrivateKey();

        Map<String, Object> access = createAccess(scope);

        Map<String, Object> header = createHeader();

        String jwt = Jwts.builder()
                         .setHeader(header)
                         .setIssuer("lang")
                         .setAudience(service)
                         .setNotBefore(new Date())
                         .setExpiration(new Date(new Date().getTime() + 1234567890123456L))
                         .setSubject("lsq")
                         .setIssuedAt(new Date())
                         .setId(UUID.randomUUID().toString())
                         .addClaims(access)
                         .signWith(p, SignatureAlgorithm.RS256)
                         .compact();
        return jwt;
    }

    private Map<String, Object> createHeader() throws CertificateException, IOException {
        Map<String, Object> header = new HashMap<>();
        header.put("kid", KeyUtils.getKeyId());
        header.put("alg", "RS256");
        header.put("typ", "JWT");
        return header;
    }

    private Map<String, Object> createAccess(String scope) {
        Map<String, Object> access = new HashMap<>();
        if (StringUtils.isBlank(scope)) {
            access.put("access", new ArrayList<>());
            return access;
        }
        String[] elements = scope.split(":");
        String resourceType = elements[0];
        String resourceName = elements[1];
        String actions = elements[2];
        Map<String, Object> resource = new HashMap<>();

        if (resourceType.equals("repository") && resourceName.equals("sec")) {
            // sec为私密空间，不允许push/pull
        } else {
            resource.put("type", resourceType);
            resource.put("name", resourceName);
            resource.put("actions", Arrays.asList(actions.split(",")));
        }
        access.put("access", Arrays.asList(resource));
        return access;
    }
}
