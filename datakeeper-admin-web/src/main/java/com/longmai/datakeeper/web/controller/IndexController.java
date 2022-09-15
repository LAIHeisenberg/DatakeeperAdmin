package com.longmai.datakeeper.web.controller;

import com.longmai.datakeeper.annotation.AnonymousAccess;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping({"","/index"})
    @AnonymousAccess
    public String index(){
        return "index";
    }


}
