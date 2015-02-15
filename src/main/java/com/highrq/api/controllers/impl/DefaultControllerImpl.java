package com.highrq.api.controllers.impl;

import com.highrq.api.controllers.DefaultController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultControllerImpl implements DefaultController {
    @RequestMapping("/api/test")
    public String test() {
        return "view";
    }

}
