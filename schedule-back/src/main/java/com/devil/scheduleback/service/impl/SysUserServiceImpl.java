package com.devil.scheduleback.service.impl;

import com.devil.scheduleback.dao.SysUserDao;
import com.devil.scheduleback.dao.impl.SysUserDaoImpl;
import com.devil.scheduleback.entity.SysUser;
import com.devil.scheduleback.service.SysUserService;

public class SysUserServiceImpl implements SysUserService {
    private SysUserDao userDao = new SysUserDaoImpl();

    @Override
    public SysUser findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public int regist(SysUser sysUser) {
        return userDao.regist(sysUser);
    }
}
