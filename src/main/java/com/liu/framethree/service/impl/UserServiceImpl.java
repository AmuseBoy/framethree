package com.liu.framethree.service.impl;

import com.liu.framethree.config.TargetDataSource;
import com.liu.framethree.dao.UserMapper;
import com.liu.framethree.entity.User;
import com.liu.framethree.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OtherServiceImpl otherService;

    @Override
    @Transactional(value = "twoTaxManager")
    //@TargetDataSource("two")
    public User getUser(Integer id) {
//        userMapper.getUser(id);
//        otherService.getUserList();
        return userMapper.getUser(id);
    }

    @Override
    @Transactional(value = "twoTaxManager")
    @TargetDataSource("two")
    public List<User> getUserList() {
        userMapper.getUser(82);
        return otherService.getUserList();
        //return userMapper.getUserList();
    }

    @Override
    public int addUser(User user){
        return userMapper.addUser(user);
    }

    @Override
    public int saveAndUpdate(User user){
        return userMapper.saveAndUpdate(user);
    }
}
