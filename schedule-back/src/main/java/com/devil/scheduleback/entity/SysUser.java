package com.devil.scheduleback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName：SysUser
 *
 * @author: Devil
 * @Date: 2024/8/31
 * @Description:
 * @version: 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SysUser {
    private Integer uid;
    private String username;
    private String userPwd;

}
