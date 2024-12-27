package com.devil.scheduleback;

import com.devil.scheduleback.service.SysUserService;
import com.devil.scheduleback.service.impl.SysScheduleServiceImpl;
import com.devil.scheduleback.service.impl.SysUserServiceImpl;
import org.junit.Test;

/**
 * ClassName：DeleteTest
 *
 * @author: Devil
 * @Date: 2024/9/7
 * @Description:
 * @version: 1.0
 */
public class DeleteTest {
    @Test
    public void test(){
        SysScheduleServiceImpl sysScheduleService = new SysScheduleServiceImpl();
        sysScheduleService.removeSchedule(3);
    }
}
