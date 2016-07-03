package com.nowcoder.service;

import com.nowcoder.dao.NewsDAO;
import com.nowcoder.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by BlueFish on 2016/7/3.
 */
@Service
public class NewsService {

    @Autowired
    private NewsDAO newsDAO;

    public List<News> queryNews(int userId, int offset, int limit){
        return newsDAO.queryAllNews(userId, offset, limit);
    }

}
