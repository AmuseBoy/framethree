package com.liu.framethree.web;

import com.liu.framethree.entity.User;
import com.liu.framethree.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 刘培振
 * @desc 用户
 * @create 2018-04-22 20:29
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    
    /**
     * @Desc 获取用户
     * @Author 刘培振
     * @Date 2018/4/22-20:36
     */
    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    public User getUser(@RequestParam Integer id){
        User user = userService.getUser(id);
        return user;
    }

    /**
     * 批量
     * @return
     */
    @RequestMapping(value = "/getUserList",method = RequestMethod.GET)
    public List<User> getUser(){
        return userService.getUserList();
    }



}
