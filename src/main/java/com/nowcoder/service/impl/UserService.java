package com.nowcoder.service.impl;

import com.nowcoder.dao.UserDAO;
import com.nowcoder.model.User;
import com.nowcoder.service.IUserService;
import com.nowcoder.util.CommonUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by BlueFish on 2016/7/3.
 */
@Service
public class UserService implements IUserService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDAO userDAO;

    @Override
    public User getUser(int id){
        List<User> users = userDAO.queryUserById(id);
        if(!CollectionUtils.isEmpty(users)){
            return users.get(0);
        }
        return null;
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
        }
        if(StringUtils.isBlank(password)){
            LOGGER.info("密码不能为空！");
            result.put("msgpassword", "密码不能为空");
        }
        User user = userDAO.queryUserByName(username);
        if(user != null){
            result.put("msgname","用户名已存在");
        }
        user = new User(username);
        String salt = UUID.randomUUID().toString();
        String md5Password = CommonUtils.md5(password + salt);
        user.setPassword(md5Password);
        user.setSalt(salt);
        user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));
        userDAO.insertUser(user);
        return result;
    }
}
