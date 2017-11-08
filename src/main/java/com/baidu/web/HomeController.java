package com.baidu.web;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baidu.bean.PostBean;

/**
 * Created by langshiquan on 17/7/14.
 */

@RestController
public class HomeController {

    @RequestMapping({"", "/"})
    public String home() {

        return "yezhaofeng";
    }

    @RequestMapping(value = "/testPost", method = RequestMethod.POST)
    public PostBean testPost(@RequestBody PostBean postBean) {

        return postBean;
    }
}
