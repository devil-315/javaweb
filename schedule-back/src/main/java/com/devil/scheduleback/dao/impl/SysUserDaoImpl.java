package com.devil.scheduleback.dao.impl;


import com.devil.scheduleback.dao.SysUserDao;
import com.devil.scheduleback.entity.SysUser;
import com.devil.scheduleback.util.JdbcUtilPtimize;

import java.lang.annotation.Annotation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//数据库连接查询
public class SysUserDaoImpl implements SysUserDao {

    @Override
    public SysUser findByUsername(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtilPtimize.getConnection();
            String sql = "select * from sys_user where username = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,username);

            resultSet = preparedStatement.executeQuery();

            SysUser sysUser = null;
            while (resultSet.next()){
                int uid = resultSet.getInt("uid");
                String username1 = resultSet.getString("username");
                String userPwd = resultSet.getString("user_pwd");

                //将查询的值，放到对象中
                sysUser = new SysUser(uid,username1,userPwd);
            }
            return sysUser;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JdbcUtilPtimize.release(connection,preparedStatement,resultSet);
        }
    }

    @Override
    public int regist(SysUser sysUser) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
            try {
                connection = JdbcUtilPtimize.getConnection();
                String sql = "insert into sys_user(username,user_pwd) values(?,?) ";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,sysUser.getUsername());
                preparedStatement.setString(2,sysUser.getUserPwd());

                int row = preparedStatement.executeUpdate();
                if(row > 0){
                    System.out.println("插入成功");
                }else {
                    System.out.println("插入失败");
                }
                return row;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                JdbcUtilPtimize.release(connection,preparedStatement,null);
            }

    }
}
