package com.superj.redis.service.impl;

import com.superj.redis.dao.UserMapper;
import com.superj.redis.pojo.User;
import com.superj.redis.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: SuperJ
 * @Date: 2019/3/24 11:33
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据id获取人员信息
     * 如果缓存存在，直接从缓存中获取
     * 如果缓存不存在，从数据库中获取，然后插入缓存
     * @param id
     * @return
     */
    @Override
    public User findById(Integer id) {
        String key = "user_"+id;
        ValueOperations<String,User> operations = redisTemplate.opsForValue();

        //判断缓存是否存在
        boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey){
            User user = operations.get(key);
            LOGGER.info("UseServiceImpl.findById：从缓存中获取了人员信息》》"+user.toString());
            return user;
        }
        //不存在，从数据库中获取
        User user = userMapper.selectByPrimaryKey(id);
        //插入缓存
        operations.set(key,user,10, TimeUnit.MINUTES);
        LOGGER.info("UseServiceImpl.findById：人员信息插入缓存中》》"+user.toString());
        return user;
    }
}
