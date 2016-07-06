package com.nowcoder.controller;

import com.nowcoder.service.IUserService;
import com.nowcoder.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 登录controller
 */
@Controller
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private IUserService userServiceImpl;

    @RequestMapping(value = "/reg/", method = {RequestMethod.POST})
    @ResponseBody
    public String registry(Model model, @RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam(value = "rember", defaultValue = "0") int rember,
                           HttpServletResponse response) {
        try {
            Map<String, Object> result = userServiceImpl.register(username, password);
            if (result.containsKey("ticket")) {
                Cookie cookie = new Cookie("ticket", (String) result.get("ticket"));
                cookie.setPath("/");// 设置cookies全站有效
                if(rember > 0){
                    cookie.setMaxAge(3600*24*5);
                }
                response.addCookie(cookie);
                return CommonUtils.getJSONString(0, "注册成功");
            } else {
                return CommonUtils.getJSONString(1, result);
            }
        } catch (Exception e) {
            LOGGER.error("注册异常", e);
            return CommonUtils.getJSONString(1, "注册异常");
        }

    }

    @RequestMapping(value = "/login/", method = {RequestMethod.POST})
    @ResponseBody
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam(value = "rember", defaultValue = "0") int rember,
                        HttpServletResponse response) {
        try {
            Map<String, Object> result = userServiceImpl.login(username, password);
            if (result.containsKey("ticket")) {
                Cookie cookie = new Cookie("ticket", (String) result.get("ticket"));
                cookie.setPath("/");// 设置cookies全站有效
                if(rember > 0){
                    cookie.setMaxAge(3600*24*5);
                }
                response.addCookie(cookie);
                return CommonUtils.getJSONString(0, "登录成功");
            } else {
                return CommonUtils.getJSONString(1, result);
            }
        } catch (Exception e) {
            LOGGER.error("登录失败", e);
            return CommonUtils.getJSONString(1, "登录失败");
        }

    }
}
