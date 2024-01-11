package com.zyx.zyxojsandbox.model;

import lombok.Data;

/**
 * 进程执行信息
 *
 * @author zyx
 * @version 1.0
 * @date 2024/1/8 008 17:48
 */
@Data
public class ExecuteMessage {

    private Integer exitValue;

    private String message;

    private String errorMessage;

    private Long time;

    private Long memory;

}
