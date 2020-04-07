package com.ozx.ozxshopportalweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: IndexController
 * @Description:TODO
 * @Author ou.zhenxing
 * @Date 2020/3/19 16:18
 * @Version： 1.0
 **/
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/index.html")
    public String indexHtml(){
        return index();
    }
}
