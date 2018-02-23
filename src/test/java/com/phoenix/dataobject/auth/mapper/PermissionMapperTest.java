package com.phoenix.dataobject.auth.mapper;

import com.phoenix.dataobject.auth.Permission;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by hjx
 * 2018/1/23 0023.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PermissionMapperTest {

    @Autowired
    private PermissionMapper permissionMapper;

    @Test
    public void selectByUserId() throws Exception {
        List<Permission> permissionList = permissionMapper.selectByUserId("1-1","0");
        Assert.assertNotEquals(0,permissionList.size());
    }



}