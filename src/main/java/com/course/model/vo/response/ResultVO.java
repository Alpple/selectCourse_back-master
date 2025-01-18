package com.course.model.vo.response;

import lombok.Data;

import java.io.Serializable;

//通用的响应对象，主要用于封装操作的结果，包括状态码、消息和数据。
@Data
public class ResultVO implements Serializable {
    //    状态常量定义
    public static final int NO_LOGIN = -1;
    public static final int SUCCESS = 0;
    public static final int FAIL = 1;
    public static final int ERROR_ROLE = 2;
    public static final int NO_PERMISSION = 3;
    public static final int INVALID_PARAM = 4;
    public static final int SERVER_ERROR = 10;
    //    序列化id
    private static final long serialVersionUID = 1L;
    //    响应字段
    private Integer code;
    private String message;
    private Object data;

    public ResultVO() {
    }

    public ResultVO(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static int getNoLogin() {
        return NO_LOGIN;
    }

    public static int getSuccess() {
        return SUCCESS;
    }

    public static int getFail() {
        return FAIL;
    }

    public static int getErrorRole() {
        return ERROR_ROLE;
    }

    public static int getNoPermission() {
        return NO_PERMISSION;
    }

    public static int getInvalidParam() {
        return INVALID_PARAM;
    }

    public static int getServerError() {
        return SERVER_ERROR;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
