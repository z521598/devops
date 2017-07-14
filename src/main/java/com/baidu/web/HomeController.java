package com.baidu.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by langshiquan on 17/7/14.
 */

@RestController
public class HomeController {

    @RequestMapping({"", "/"})
    public String home() {

        return "101.236.1.205";
    }
}
