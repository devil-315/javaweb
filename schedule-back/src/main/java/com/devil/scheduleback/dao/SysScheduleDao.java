package com.devil.scheduleback.dao;

import com.devil.scheduleback.entity.SysSchedule;

import java.util.List;

/**
 * ClassName：SysScheduleDao
 *
 * @author: Devil
 * @Date: 2024/9/7
 * @Description:
 * @version: 1.0
 */
public interface SysScheduleDao {
    List<SysSchedule> findItemLIstByUid(int uid);

    int removeSchedule(int sid);

    int updateSchedule(SysSchedule sysSchedule);

    void addDefaultSchedule(int uid);
}
