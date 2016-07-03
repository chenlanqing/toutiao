package com.nowcoder.service;

import com.nowcoder.dao.UserDAO;
import com.nowcoder.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by BlueFish on 2016/7/3.
 */
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public User getUser(int id){
        List<User> users = userDAO.queryUserById(id);
        if(!CollectionUtils.isEmpty(users)){
            return users.get(0);
        }
        return null;
    }
}
