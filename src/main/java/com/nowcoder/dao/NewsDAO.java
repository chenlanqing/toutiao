package com.nowcoder.dao;

import com.nowcoder.model.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by BlueFish on 2016/7/2.
 */
@Mapper
public interface NewsDAO {

    /**
     * 查询所有的新闻资讯
     * @param userId    用户编号
     * @param offset    分页参数
     * @param limit     每页数据量
     * @return
     */
    List<News> queryAllNews(@Param("userId") Integer userId, @Param("offset") int offset,
                            @Param("limit") int limit);
    /**
     * 插入单条数据
     * @param news
     * @return
     */
    int insertOne(News news);

    /**
     * 批量插入数据
     * @param news
     * @return
     */
    int insertMany(List<News> news);

}
