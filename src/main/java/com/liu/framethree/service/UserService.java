package com.liu.framethree.service;

import com.liu.framethree.model.User;

import java.util.List;

public interface UserService {

    User getUser(Integer id);

    List<User> getUserList();

    int addUser(User user);

    int saveAndUpdate(User user);
}
