package com.devil.scheduleback.common;

/**
 * ClassName：ResultEnum
 *
 * @author: Devil
 * @Date: 2024/8/31
 * @Description:
 * @version: 1.0
 */
//响应数据公共类
public enum ResultEnum {
    SUCCESS(200,"success"),
    ERROR(500,"error"),
    USERNAME_ERROR(501,"usernameError"),
    PASSWORD_ERROR(502,"passwordError"),
    NOTLOGIN(503,"notLogin"),
    USERNAME_USED(505,"usernameUsed");


    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
