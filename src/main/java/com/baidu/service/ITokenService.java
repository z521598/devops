package com.baidu.service;

public interface ITokenService {
    String getToken(String service, String scope) throws Exception;
}
