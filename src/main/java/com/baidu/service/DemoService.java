package com.baidu.service;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/2/28.
 */
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Service("demo")
public class DemoService implements IDemoService{

    @Override
    public int add(int i, int j) {
        return i+j;
    }

}
