package com.superj.mybatis.service.impl;

import com.superj.mybatis.dao.UserMapper;
import com.superj.mybatis.pojo.User;
import com.superj.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: SuperJ
 * @Date: 2019/3/24 11:33
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
