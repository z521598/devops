package com.baidu.service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {

    private Map<String, String> userAuth = new HashMap<>();

    {
        userAuth.put("user1", "pwd1");
        userAuth.put("user2", "pwd2");
    }

    @Override
    public boolean login(String base64Header) {
        final Base64 base64 = new Base64();
        try {
            String userAndPwd = new String(base64.decode(base64Header.replaceAll("Basic ", "")), "UTF-8");
            System.out.println(userAndPwd);
            String[] userAndPwdArr = userAndPwd.split(":");
            String realPwd = userAuth.get(userAndPwdArr[0]);
            if (realPwd == null) {
                return false;
            }
            if (userAndPwdArr[1].equals(realPwd)) {
                return true;
            }
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return false;
    }
}
