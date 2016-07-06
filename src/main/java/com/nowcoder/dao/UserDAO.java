package com.nowcoder.dao;

import com.nowcoder.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by BlueFish on 2016/7/2.
 */
@Mapper
public interface UserDAO {

    /**
     * 查询数据，如果id为0，则是查询全部数据
     * @param id
     * @return
     */
    List<User> queryUserById(@Param("id") int id);

    /**
     * 插入数据
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 更新数据
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 删除数据
     * @param id
     * @return
     */
    int deleteUser(@Param("id") int id);

    /**
     * 按名称查找用户
     * @param name
     * @return
     */
    User queryUserByName(String name);

}
