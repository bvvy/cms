package org.bvvy.cms.controller;

import org.bvvy.cms.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    private IUserService userService;
    @RequestMapping("/admin/index")
    public String index() {
        return "admin/index";
    }
}
