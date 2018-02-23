package com.phoenix.dataobject.auth.mapper;

import com.phoenix.dataobject.auth.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by hjx
 * 2018/1/23 0023.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectByUserId() throws Exception {
        User user = userMapper.selectByUserId("1-1");
        Assert.assertNotNull(user);
    }

    @Test
    public void selectByUserName() throws Exception {
        User user = userMapper.selectByUserName("admin",null);
        Assert.assertNotNull(user);
    }

}