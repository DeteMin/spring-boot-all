package com.superj.redis.controller;

import com.superj.redis.pojo.User;
import com.superj.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: SuperJ
 * @Date: 2019/3/24 13:46
 * @Description:
 */
@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/findUserById",method = RequestMethod.GET)
    public User findUserById(@RequestParam(value = "id",required = true) Integer id){
        return userService.findById(id);
    }
}
