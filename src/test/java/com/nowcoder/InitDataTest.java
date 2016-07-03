package com.nowcoder;

import com.nowcoder.dao.NewsDAO;
import com.nowcoder.dao.UserDAO;
import com.nowcoder.model.News;
import com.nowcoder.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.Random;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ToutiaoApplication.class)
public class InitDataTest {

    @Autowired
    private NewsDAO newsDAO;

    @Autowired
    private UserDAO userDAO;

    @Test
    public void test1() {
        Random random = new Random();
        for (int i = 0; i < 10; ++i) {
            User user = new User();
            user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
            user.setName(String.format("USER%d", i));
            user.setPassword("nowcoderpassword");
            user.setSalt("");
            userDAO.insertUser(user);
        }
        List<User> users = userDAO.queryUserById(0);
        Assert.assertEquals(users.size(), 10);
    }

    @Test
    public void test2() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            News news = new News();
            news.setCommentCount(i);
            Date date = new Date();
            date.setTime(date.getTime() - 1000*3600*5*i);
            news.setCreatedDate(date);
            news.setImage(String.format("http://images.nowcoder.com/head/%dm.png", random.nextInt(1000)));
            news.setLikeCount(i+1);
            news.setUserId(i+1);
            news.setTitle(String.format("TITLE{%d}", i));
            news.setLink(String.format("http://www.nowcoder.com/%d.html", i));
            newsDAO.insertOne(news);
        }
    }
}
