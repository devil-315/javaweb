package com.devil.scheduleback;

import com.devil.scheduleback.entity.SysSchedule;
import com.devil.scheduleback.service.impl.SysScheduleServiceImpl;
import org.junit.Test;

/**
 * ClassName：UpdateTest
 *
 * @author: Devil
 * @Date: 2024/9/7
 * @Description:
 * @version: 1.0
 */
public class UpdateTest {
    @Test
    public void test(){
        SysScheduleServiceImpl sysScheduleService = new SysScheduleServiceImpl();
        SysSchedule test = new SysSchedule(2, 1, "如履薄冰", 0);
        sysScheduleService.updateSchedule(test);

    }
}
