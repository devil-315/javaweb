package com.devil.scheduleback.util;

import com.devil.scheduleback.common.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * ClassName：WebUtil
 *
 * @author: Devil
 * @Date: 2024/9/7
 * @Description:
 * @version: 1.0
 */
public class WebUtil {
    private static ObjectMapper objectMapper;
    //初始化objectMapper
    static {
        objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    //从请求中获取JSON并且转为Object
    public static <T> T readJson(HttpServletRequest request,Class<T> clazz){
        T t =null;
        BufferedReader reader = null;
        try {
            reader = request.getReader();
            StringBuffer buffer = new StringBuffer();
            String line = null;
            while ((line = reader.readLine()) != null){
                buffer.append(line);
            }

            t = objectMapper.readValue(buffer.toString(),clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return t;
    }

    //将Result对象转成Json字符串，并且放到响应对象中
    public static void writeJson(HttpServletResponse resp, Result result){
        resp.setContentType("application/json;charset=UTF-8");
        try {
            String json= objectMapper.writeValueAsString(result);
            resp.getWriter().write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
