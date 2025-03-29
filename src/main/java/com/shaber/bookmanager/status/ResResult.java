package com.shaber.bookmanager.status;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResResult implements Serializable {

    private static final long serialVersionUID = -2540764645447402367L;

    /**
     * 状态码，参与{@link ResStatus}
     */
    private Integer code;

    /**
     * 状态信息
     */
    private String msg;

    /**
     * 请求处理结果返回的时间戳
     */
    private String timestamp;

    /**
     * 响应数据
     */
    private Object data;

    public static ResResult success(Object data) {
        ResResult result = new ResResult();
        result.code = ResStatus.SUCCESS.getCode();
        result.msg = ResStatus.SUCCESS.getDesc();
        result.timestamp = String.valueOf(Instant.now().toEpochMilli());
        result.data = data;
        return result;
    }

    public static ResResult error(String msg) {
        ResResult result = new ResResult();
        result.code = ResStatus.ERROR.getCode();
        result.timestamp = String.valueOf(Instant.now().toEpochMilli());
        result.msg = msg;
        return result;
    }

    public static ResResult info(ResStatus status, Object data) {
        ResResult result = new ResResult();
        result.code = status.getCode();
        result.msg = status.getDesc();
        result.timestamp = String.valueOf(Instant.now().toEpochMilli());
        result.data = data;
        return result;
    }

    public static ResResult info(ResStatus status, String msg, Object data) {
        ResResult result = new ResResult();
        result.code = status.getCode();
        result.timestamp = String.valueOf(Instant.now().toEpochMilli());
        result.msg = msg;
        result.data = data;
        return result;
    }

}
