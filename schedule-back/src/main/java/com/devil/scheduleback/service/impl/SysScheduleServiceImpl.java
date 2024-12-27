package com.devil.scheduleback.service.impl;

import com.devil.scheduleback.dao.SysScheduleDao;
import com.devil.scheduleback.dao.impl.SysScheduleDaoImpl;
import com.devil.scheduleback.entity.SysSchedule;
import com.devil.scheduleback.service.SysScheduleService;

import java.util.List;

/**
 * ClassName：SysScheduleServiceImpl
 *
 * @author: Devil
 * @Date: 2024/9/7
 * @Description:
 * @version: 1.0
 */
public class SysScheduleServiceImpl implements SysScheduleService {

    private SysScheduleDao scheduleDao = new SysScheduleDaoImpl();

    @Override
    public List<SysSchedule> findItemLIstByUid(int uid) {
        return scheduleDao.findItemLIstByUid(uid);
    }

    @Override
    public int removeSchedule(int sid) {
        return scheduleDao.removeSchedule(sid);
    }

    @Override
    public int updateSchedule(SysSchedule sysSchedule) {
        return scheduleDao.updateSchedule(sysSchedule);
    }

    @Override
    public void addDefaultSchedule(int uid) {
        scheduleDao.addDefaultSchedule(uid);
    }
}
