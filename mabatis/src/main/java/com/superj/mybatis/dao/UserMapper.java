package com.superj.mybatis.dao;

import com.superj.mybatis.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * User表的dao接口
 */
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}