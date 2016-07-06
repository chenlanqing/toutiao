package com.nowcoder.controller;

import com.nowcoder.service.impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 登录controller
 */
@Controller
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value="/reg/", method = {RequestMethod.POST})
    public String registry(Model model, @RequestParam("username")String username,
                           @RequestParam("password")String password,
                           @RequestParam(value="rember", defaultValue = "0")String rember){
        Map<String, Object> result = userService.register(username, password);

        return null;
    }
}
