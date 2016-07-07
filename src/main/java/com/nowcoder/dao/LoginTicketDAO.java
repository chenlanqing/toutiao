package com.nowcoder.dao;

import com.nowcoder.model.LoginTicket;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by BlueFish on 2016/7/6.
 */
@Mapper
public interface LoginTicketDAO {

    /**
     * 根据ticket查询登录信息
     * @param ticket
     * @return
     */
    LoginTicket queryTicket(String ticket);

    /**
     * 插入ticket信息
     * @param ticket
     * @return
     */
    int insertOne(LoginTicket ticket);

    /**
     * 更新 ticket状态，设置为 1
     *
     * @param ticket
     * @return
     */
    int updateTicket(LoginTicket ticket);
}
