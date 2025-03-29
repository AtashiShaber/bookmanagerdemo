package com.shaber.bookmanager.status;

import lombok.Getter;

@Getter
public enum ResStatus {
    /**
     * 请求成功
     */
    SUCCESS(200, "处理成功"),
    /**
     * 请求处理失败
     */
    FAIL(400, "处理失败"),
    /**
     * 系统异常
     */
    ERROR(500, "系统异常");

    ResStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 状态码
     */
    private final Integer code;
    /**
     * 状态信息
     */
    private final String desc;
}
