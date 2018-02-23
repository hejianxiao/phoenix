package com.phoenix.service.auth.impl;

import com.phoenix.dto.auth.PermissionDTO;
import com.phoenix.service.auth.PermissionService;
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
public class PermissionServiceImplTest {

    private static final String TOP_PARENT_ID = "0";

    @Autowired
    private PermissionService permissionService;


    @Test
    public void findPermission() throws Exception {
        List<PermissionDTO> permissionDTOList = permissionService.findPermission("1-1", TOP_PARENT_ID);
        Assert.assertNotEquals(0,permissionDTOList.size());
    }
}