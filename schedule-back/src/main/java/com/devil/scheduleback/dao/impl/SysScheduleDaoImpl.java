package com.devil.scheduleback.dao.impl;

import com.devil.scheduleback.dao.SysScheduleDao;
import com.devil.scheduleback.entity.SysSchedule;
import com.devil.scheduleback.util.JdbcUtilPtimize;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SysScheduleDaoImpl implements SysScheduleDao {
    @Override
    public List<SysSchedule> findItemLIstByUid(int uid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtilPtimize.getConnection();

            String sql = "select sid,uid,title,completed from sys_schedule where uid = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,uid);

            resultSet = preparedStatement.executeQuery();
            //创建ArrayList
            ArrayList<SysSchedule> scheduleList = new ArrayList<>();
            SysSchedule sysSchedule = null;
            while (resultSet.next()){
                int sid = resultSet.getInt("sid");
                int uid1 = resultSet.getInt("uid");
                String title = resultSet.getString("title");
                int completed = resultSet.getInt("completed");
                //将查询的值，放到对象中
                sysSchedule = new SysSchedule(sid, uid1, title, completed);

                //将对象放到集合中
                scheduleList.add(sysSchedule);
            }
            return scheduleList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JdbcUtilPtimize.release(connection,preparedStatement,resultSet);
        }
    }

    @Override
    public int removeSchedule(int sid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtilPtimize.getConnection();

            String sql = "DELETE FROM sys_schedule where sid = ?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,sid);

            int row = preparedStatement.executeUpdate();
            if(row > 0){
                System.out.println("删除成功");
            }else {
                System.out.println("删除失败");
            }
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JdbcUtilPtimize.release(connection,preparedStatement,null);
        }
    }

    @Override
    public int updateSchedule(SysSchedule sysSchedule) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtilPtimize.getConnection();
            String sql = "UPDATE sys_schedule set title = ?, completed = ? where sid = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,sysSchedule.getTitle());
            preparedStatement.setInt(2,sysSchedule.getCompleted());
            preparedStatement.setInt(3,sysSchedule.getSid());

            int i = preparedStatement.executeUpdate();
            if( i > 0){
                System.out.println("更新成功");
            }else {
                System.out.println("更新失败");
            }
            return i;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JdbcUtilPtimize.release(connection,preparedStatement,null);
        }
    }

    @Override
    public void addDefaultSchedule(int uid) {
        Connection connection =null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtilPtimize.getConnection();

//            String sql = "insert into sys_schedule values(Default,?,'请输入历程',0)";
            String sql = "insert into sys_schedule(uid,title,completed) values(?,'请输入历程',0)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,uid);
            int raw = preparedStatement.executeUpdate();

            if(raw > 0){
                System.out.println("新增成功");
            }else {
                System.out.println("新增失败");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
