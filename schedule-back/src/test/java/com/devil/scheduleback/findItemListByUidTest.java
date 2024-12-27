package com.devil.scheduleback;

import com.devil.scheduleback.dao.SysScheduleDao;
import com.devil.scheduleback.dao.SysUserDao;
import com.devil.scheduleback.dao.impl.SysScheduleDaoImpl;
import com.devil.scheduleback.entity.SysSchedule;
import org.junit.Test;

import java.util.List;

/**
 * ClassName：findItemListByUidTest
 *
 * @author: Devil
 * @Date: 2024/9/7
 * @Description:
 * @version: 1.0
 */
public class findItemListByUidTest {
    @Test
    public void Test(){
        SysScheduleDao sysScheduleDao = new SysScheduleDaoImpl();
        List<SysSchedule> itemLIstByUid = sysScheduleDao.findItemLIstByUid(1);
        System.out.println("=============");
        System.out.println(itemLIstByUid);
    }
}
