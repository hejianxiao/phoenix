package com.phoenix.service.auth.impl;

import com.github.pagehelper.Page;
import com.phoenix.dataobject.auth.Role;
import com.phoenix.service.auth.RoleService;
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
 * 2018/1/24 0024.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleServiceImplTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void findByUserId() throws Exception {
        List<Role> roleList = roleService.findByUserId("1-1");
        Assert.assertNotEquals(0,roleList.size());
    }

    @Test
    public void findRolePage() throws Exception {
        Page<Role> rolePage = roleService.findRolePage(1,5);
        Assert.assertNotEquals(0,rolePage.getResult().size());
    }

}