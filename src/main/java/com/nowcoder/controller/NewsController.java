package com.nowcoder.controller;

import com.nowcoder.service.INewsService;
import com.nowcoder.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by BlueFish on 2016/7/12.
 */
@Controller
public class NewsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private INewsService newsService;

    @RequestMapping(value="/uploadImage" ,method = {RequestMethod.POST})
    @ResponseBody
    public String uploadImage(@RequestParam("file") MultipartFile file){
        try {
            String fileUrl = newsService.saveImage(file);
            if(fileUrl == null){
                return CommonUtils.getJSONString(1, "upload image failed");
            }
            return CommonUtils.getJSONString(0, fileUrl);
        } catch (Exception e) {
            LOGGER.error("upload image failed", e);
            return CommonUtils.getJSONString(1, "upload image failed");
        }
    }
}
