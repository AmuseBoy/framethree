package com.liu.framethree.dao;

import com.liu.framethree.config.TargetDataSource;
import com.liu.framethree.model.User;

import java.util.List;

public interface UserMapper {

    User getUser(Integer id);

    User getUserByName(String name);

    List<User> getUserList();

    int addUser(User user);

    int saveAndUpdate(User user);
}
