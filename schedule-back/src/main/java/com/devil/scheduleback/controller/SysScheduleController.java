package com.devil.scheduleback.controller;

import com.devil.scheduleback.common.Result;
import com.devil.scheduleback.common.ResultEnum;
import com.devil.scheduleback.entity.SysSchedule;
import com.devil.scheduleback.service.SysScheduleService;
import com.devil.scheduleback.service.impl.SysScheduleServiceImpl;
import com.devil.scheduleback.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * ClassName：SysScheduleController
 *
 * @author: Devil
 * @Date: 2024/9/7
 * @Description:
 * @version: 1.0
 */
@WebServlet("/schedule/*")
public class SysScheduleController extends HttpServlet {
    private SysScheduleService scheduleService = new SysScheduleServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        String path = split[split.length - 1];
        if(path.equals("findAllSchedule")){
            findAllSchedule(req,resp);
        } else if (path.equals("removeSchedule")) {
            removeSchedule(req,resp);
        }else if (path.equals("updateSchedule")){
            updateSchedule(req,resp);
        } else if (path.equals("addDefaultSchedule")) {
            addDefaultSchedule(req,resp);
        }
    }

    //查询用户所有历程
    public void findAllSchedule(HttpServletRequest req,HttpServletResponse resp){
        //1.接收请求中的参数
        int uid =Integer.parseInt(req.getParameter("uid"));
        //2.调用service层，查询用户的所有历程
        List<SysSchedule> itemList =scheduleService.findItemLIstByUid(uid);

        HashMap<String, List<SysSchedule>> data = new HashMap<>();
        data.put("itemList",itemList);
        //将数据放入Result对象中
        Result result = Result.ok(data);

        //将Result对象 转换成json 响应给 客户端
        WebUtil.writeJson(resp,result);
    }

    //删除
    public void removeSchedule(HttpServletRequest req,HttpServletResponse resp){
        //获取参数
        int sid = Integer.parseInt(req.getParameter("sid"));
        //调用服务层方法，删除
        int row = scheduleService.removeSchedule(sid);

        Result result = null;
        if(row < 1){
            result = Result.build(null, ResultEnum.ERROR);
        }else {
            result = Result.ok(null);
        }
        WebUtil.writeJson(resp,result);
    }

    //更新
    public void updateSchedule(HttpServletRequest req,HttpServletResponse resp){
        //获取请求中的json,并且转成SysSchedule
        SysSchedule sysSchedule = WebUtil.readJson(req, SysSchedule.class);
        //调用服务层的更新
        int row =scheduleService.updateSchedule(sysSchedule);

        Result result = Result.ok(null);
        WebUtil.writeJson(resp,result);
    }

    //添加
    public void addDefaultSchedule(HttpServletRequest req,HttpServletResponse resp){
        int uid =Integer.parseInt(req.getParameter("uid"));

        //调用服务层 向数据库添加一条空数据
        scheduleService.addDefaultSchedule(uid);

        WebUtil.writeJson(resp,Result.ok(null));
    }
}
