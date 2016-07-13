package com.nowcoder.service.impl;

import com.nowcoder.common.CommonConstant;
import com.nowcoder.dao.NewsDAO;
import com.nowcoder.model.News;
import com.nowcoder.service.INewsService;
import com.nowcoder.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

/**
 * Created by BlueFish on 2016/7/3.
 */
@Service
public class NewsServiceImpl implements INewsService{

    @Autowired
    private NewsDAO newsDAO;

    public List<News> queryNews(int userId, int offset, int limit){
        return newsDAO.queryAllNews(userId, offset, limit);
    }

    @Override
    public String saveImage(MultipartFile file) throws Exception{
        String originName = file.getOriginalFilename();
        if(originName == null || originName.lastIndexOf(".") < 0){
            return null;
        }
        String fileExt = originName.substring(originName.lastIndexOf(".")+1).toLowerCase();
        if(!CommonUtils.isAllowedUploadImage(fileExt, CommonConstant.IMAGE_EXT)){
            return null;
        }
        String fileName = UUID.randomUUID().toString().replaceAll("-","") + "." + fileExt;
        FileCopyUtils.copy(file.getInputStream(), new FileOutputStream("D:/tmp/" + fileName));
        return "D:/tmp/" + fileName;
    }

}
