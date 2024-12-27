package com.devil.scheduleback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName：SysSchedule
 *
 * @author: Devil
 * @Date: 2024/8/31
 * @Description:
 * @version: 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SysSchedule {
    private Integer sid;
    private Integer uid;
    private String title;
    private Integer completed;
}

