package com.nowcoder.service;

import com.nowcoder.model.User;

import java.util.Map;

/**
 * 用户服务类接口
 */
public interface IUserService {
    /**
     * 根据用户编号获取用户信息
     * @param id
     * @return
     */
    public User getUser(int id);

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    public int addUser(User user);

    /**
     * 注册信息
     * @param username
     * @param password
     * @return
     */
    public Map<String, Object> register(String username, String password);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public Map<String, Object> login(String username, String password);

}
