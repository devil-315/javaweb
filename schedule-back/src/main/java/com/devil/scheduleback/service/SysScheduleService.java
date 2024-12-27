package com.devil.scheduleback.service;

import com.devil.scheduleback.entity.SysSchedule;

import java.util.List;

//业务逻辑层
public interface SysScheduleService {
    List<SysSchedule> findItemLIstByUid(int uid);

    int removeSchedule(int sid);

    int updateSchedule(SysSchedule sysSchedule);

    void addDefaultSchedule(int uid);
}
