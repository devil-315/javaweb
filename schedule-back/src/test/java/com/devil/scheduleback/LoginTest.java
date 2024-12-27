package com.devil.scheduleback;

import com.devil.scheduleback.entity.SysUser;
import com.devil.scheduleback.service.SysUserService;
import com.devil.scheduleback.service.impl.SysUserServiceImpl;
import org.junit.Test;

/**
 * ClassName：LoginTest
 *
 * @author: Devil
 * @Date: 2024/9/7
 * @Description:
 * @version: 1.0
 */
public class LoginTest {
    private SysUserService userService = new SysUserServiceImpl();
    @Test
    public void test(){
        SysUser sysUser = userService.findByUsername("devil123");
        System.out.println(sysUser);
    }

    @Test
    public void registTest(){
        SysUser sysUser = new SysUser();
        sysUser.setUsername("haha");
        sysUser.setUserPwd("123456");
        userService.regist(sysUser);
    }
}
