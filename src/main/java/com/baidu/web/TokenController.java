package com.baidu.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baidu.service.ILoginService;
import com.baidu.service.ITokenService;

@RestController
@RequestMapping("/v2")
public class TokenController {

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private ILoginService loginService;

    @RequestMapping("/token")
    public Map<String, String> getToken(HttpServletRequest request, String service, String scope, boolean offline_token,
                                        String client_id, String account) throws Exception {
        System.out.println("Authorization Header: ");
        System.out.print(request.getHeader("Authorization"));
        System.out.println();

        boolean isSucc = loginService.login(request.getHeader("Authorization"));
        System.out.println("login: " + isSucc);

        Map<String, String> tokenMap = new HashMap<>();
        if (!isSucc) {
            tokenMap.put("token", "{}");
            return tokenMap;
        }

        System.out.println("Query String: ");
        System.out.println("service: " + service);
        System.out.println("offline_token: " + offline_token);
        System.out.println("client_id: " + client_id);
        System.out.println("account: " + account);
        System.out.println("scope: " + scope);
        System.out.println();

        String jwt = tokenService.getToken(service, scope);
        System.out.println(jwt);

        tokenMap.put("token", jwt);
        return tokenMap;
    }

}
