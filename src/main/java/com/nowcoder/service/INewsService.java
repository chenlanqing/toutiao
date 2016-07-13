package com.nowcoder.service;

import com.nowcoder.model.News;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 资讯接口类
 */
public interface INewsService {

    public List<News> queryNews(int userId, int offset, int limit);


    public String saveImage(MultipartFile file)throws Exception;
}
