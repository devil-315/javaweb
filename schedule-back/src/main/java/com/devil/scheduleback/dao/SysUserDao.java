package com.devil.scheduleback.dao;

import com.devil.scheduleback.entity.SysUser;

//数据库接口层
public interface SysUserDao {
    SysUser findByUsername(String username);

    int regist(SysUser sysUser);
}
