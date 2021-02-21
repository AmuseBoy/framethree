package com.liu.framethree;

import com.liu.framethree.model.User;
import com.liu.framethree.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class FramethreeApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
        User user = new User("小黑", 31, "女", new Date(), "123@123.com", "123");
        int i = userService.addUser(user);
        System.out.println(i==1?"成功":"失败");
    }

}
