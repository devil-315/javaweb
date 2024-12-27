package com.devil.scheduleback.controller;

import com.devil.scheduleback.common.Result;
import com.devil.scheduleback.common.ResultEnum;
import com.devil.scheduleback.entity.SysUser;
import com.devil.scheduleback.service.SysUserService;
import com.devil.scheduleback.service.impl.SysUserServiceImpl;
import com.devil.scheduleback.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;

/**
 * ClassName：SysUserController
 *
 * @author: Devil
 * @Date: 2024/8/31
 * @Description:
 * @version: 1.0
 */
@WebServlet("/user/*")
public class SysUserController extends HttpServlet {
    private SysUserService userService = new SysUserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        String path = split[split.length - 1];
        System.out.println(path);
        if(path.contains("login")){
            login(req,resp);
        } else if (path.equals("checkUsernameUsed")) {
            checkUsernameUsed(req,resp);
        } else if (path.equals("regist")) {
            regist(req,resp);
        }
    }

    //检查用户名是否可用
    public void checkUsernameUsed(HttpServletRequest req, HttpServletResponse resp){
        //1.接收用户名
        String username = req.getParameter("username");

        //2.调用服务层，根据用户名查询用户信息
        SysUser loginUser = userService.findByUsername(username);

        Result result = Result.ok(null);

        //TODO 如果数据库已经存在该用户,响应已经占用 code = 505 ,message="USERNAME_USED"
        if(loginUser != null){
            result = Result.build(null,ResultEnum.USERNAME_USED);
        }
        //如果数据库不存在该用户,直接响应空数据并且code = 200
        WebUtil.writeJson(resp,result);
    }

    //登录
    public void login(HttpServletRequest req, HttpServletResponse resp){
        //读取JSON字符串中的数据
        //1.接收用户名和密码
        SysUser sysUser = WebUtil.readJson(req, SysUser.class);

        //2.调用服务层 根据用户名和密码查询用户信息
        SysUser loginUser = userService.findByUsername(sysUser.getUsername());

        System.out.println(loginUser);

        Result result = null;
        if(loginUser == null){
            //用户名错误
            result = Result.build(null, ResultEnum.USERNAME_ERROR);
        }else if(!sysUser.getUserPwd().equals(loginUser.getUserPwd())){
            //密码错误
            result = Result.build(null,ResultEnum.PASSWORD_ERROR);
        }else {
            //用户名，密码正确 将sysUser 对象传给客户端
            HashMap<String, SysUser> data = new HashMap<>();
            loginUser.setUserPwd("");
            data.put("loginUser",loginUser);
            result = Result.ok(data);
        }
        //将结果响应给客户端
        WebUtil.writeJson(resp,result);
    }

    //注册
    public void regist(HttpServletRequest req, HttpServletResponse resp){
        //1.接收参数
        SysUser sysUser = WebUtil.readJson(req, SysUser.class);
        //判断用户名是否可用
        SysUser registUser = userService.findByUsername(sysUser.getUsername());
        Result result;
        //如果不占用，注册
        if(registUser == null){
            int row = userService.regist(sysUser);
            if(row < 1){
                result = Result.build(null,ResultEnum.USERNAME_USED);
            }else {
                result = Result.ok(null);
            }
        }else {
            //如果占用直接返回用户名已经被使用
            result = Result.build(null,ResultEnum.USERNAME_USED);
        }
        WebUtil.writeJson(resp,result);
    }
}
