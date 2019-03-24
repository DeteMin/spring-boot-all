package com.superj.redis.service;


import com.superj.redis.pojo.User;

/**
 * @Auther: SuperJ
 * @Date: 2019/3/24 11:31
 * @Description:User表业务逻辑接口
 */
public interface UserService {
    /**
     * 根据id查询人员信息，这里测试表中id是Integer，实际中应该是String
     * @param id
     * @return
     */
    User findById(Integer id);
}
