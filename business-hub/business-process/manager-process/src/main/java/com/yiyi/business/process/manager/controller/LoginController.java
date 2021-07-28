package com.yiyi.business.process.manager.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LoginController {

    @GetMapping(value = "/login")
    public String createUser(String username, String password, String rememberMe, Model model) {
        log.info("username : {}",username);
        return "index";
    }
}
