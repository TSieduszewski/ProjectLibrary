package com.crm.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MenuConroller {

    @RequestMapping("/menu")
    public String menu(){
        return "menu/menuPage";
    }
}
