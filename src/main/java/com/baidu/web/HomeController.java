package com.baidu.web;

import com.baidu.AopTargetUtils;
import com.baidu.service.IDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baidu.bean.PostBean;

/**
 * Created by langshiquan on 17/7/14.
 */

@RestController
public class HomeController implements ApplicationContextAware{

    Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private IDemoService demo1;

    private IDemoService demo2;
    @RequestMapping({"", "/"})
    public String home() {
        return "222";
    }

    @RequestMapping(value = "/testPost", method = RequestMethod.POST)
    public PostBean testPost(@RequestBody PostBean postBean) {

        return postBean;
    }
    @RequestMapping("/demo1")
    public String demo1(){
        long start = System.currentTimeMillis();
        demo1.add(1,2);
        long end = System.currentTimeMillis();
        logger.info("demo1: {}",end-start);
        return "";
    }

    @RequestMapping("/demo2")
    public String demo2(){
        long start = System.currentTimeMillis();
        demo2.add(1,2);
        long end = System.currentTimeMillis();
        logger.info("demo2: {}",end-start);
        return "";
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        try {
            demo2 = (IDemoService) AopTargetUtils.getTarget(applicationContext.getBean("demo"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
