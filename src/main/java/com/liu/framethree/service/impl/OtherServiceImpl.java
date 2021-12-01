package com.liu.framethree.service.impl;

import com.liu.framethree.config.TargetDataSource;
import com.liu.framethree.dao.UserMapper;
import com.liu.framethree.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName OtherServiceImpl
 * @Description TODO
 * @Author 刘培振
 * @Date 2020-05-22 16:32
 * @Version 1.0
 */
@Service
public class OtherServiceImpl {

    @Autowired
    private UserMapper userMapper;

//    @Transactional(value = "twoTaxManager")
    @TargetDataSource("one")
    public List<User> getUserList() {
        return userMapper.getUserList();
    }
}
