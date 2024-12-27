package com.devil.scheduleback.service;

import com.devil.scheduleback.entity.SysUser;

//业务逻辑接口
public interface SysUserService {
    //根据用户名查询对象
    SysUser findByUsername(String username);

    int regist(SysUser sysUser);
}
