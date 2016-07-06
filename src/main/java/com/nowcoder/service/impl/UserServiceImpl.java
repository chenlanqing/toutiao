package com.nowcoder.service.impl;

import com.nowcoder.dao.UserDAO;
import com.nowcoder.model.User;
import com.nowcoder.service.ILoginTicketService;
import com.nowcoder.service.IUserService;
import com.nowcoder.util.CommonUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by BlueFish on 2016/7/3.
 */
@Service
public class UserServiceImpl implements IUserService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ILoginTicketService loginTicketServiceImpl;

    @Override
    public User getUser(int id){
        User users = userDAO.queryUserById(id);
        return users;
    }

    @Override
    public int addUser(User user) {
        int result = userDAO.insertUser(user);
        return result;
    }

    @Override
    public Map<String, Object> register(String username, String password) {
        Map<String, Object> result = new HashMap<>();
        if(StringUtils.isBlank(username)){
            LOGGER.info("用户名不能为空！");
            result.put("msgname", "用户名不能为空");
            return result;
        }
        if(StringUtils.isBlank(password)){
            LOGGER.info("密码不能为空！");
            result.put("msgpwd", "密码不能为空");
            return result;
        }
        // 密码强度的判断，密码是否跟用户名一样

        User user = userDAO.queryUserByName(username);
        if(user != null){
            result.put("msgname","用户名已存在");
            return result;
        }
        user = new User(username);
        // 获取salt，对密码进行加密
        String salt = UUID.randomUUID().toString();
        String md5Password = CommonUtils.md5(password + salt);
        user.setPassword(md5Password);
        user.setSalt(salt);
        user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));
        userDAO.insertUser(user);

        String ticket = loginTicketServiceImpl.addLoginTicket(user.getId());
        result.put("ticket", ticket);
        return result;
    }

    @Override
    public Map<String, Object> login(String username, String password) {
        Map<String, Object> result = new HashMap<>();
        if(StringUtils.isBlank(username)){
            LOGGER.info("用户名不能为空！");
            result.put("msgname", "用户名不能为空");
            return result;
        }
        if(StringUtils.isBlank(password)){
            LOGGER.info("密码不能为空！");
            result.put("msgpwd", "密码不能为空");
            return result;
        }
        // 密码强度的判断，密码是否跟用户名一样

        User user = userDAO.queryUserByName(username);
        if(user == null){
            result.put("msgname","用户名不存在");
            return result;
        }
        String ticket = loginTicketServiceImpl.addLoginTicket(user.getId());
        result.put("ticket", ticket);
        return result;
    }
}
