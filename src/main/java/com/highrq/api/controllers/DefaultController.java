package com.highrq.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
    @RequestMapping("/api/test")
    public String test() {
        return "view";
    }

}
