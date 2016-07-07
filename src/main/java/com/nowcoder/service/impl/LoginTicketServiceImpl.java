package com.nowcoder.service.impl;

import com.nowcoder.dao.LoginTicketDAO;
import com.nowcoder.model.LoginTicket;
import com.nowcoder.service.ILoginTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * Created by BlueFish on 2016/7/6.
 */
@Service
public class LoginTicketServiceImpl implements ILoginTicketService {

    @Autowired
    private LoginTicketDAO loginTicketDAO;

    @Override
    public String addLoginTicket(int userId) {
        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(userId);
        ticket.setStatus(0);
        Date date = new Date();
        date.setTime(date.getTime() + 1000 * 3600 * 24);
        ticket.setExpired(date);
        ticket.setTicket(UUID.randomUUID().toString().replace("-",""));
        loginTicketDAO.insertOne(ticket);
        return ticket.getTicket();
    }
}
