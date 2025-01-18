package com.course.controller;

import com.course.model.vo.response.ResultVO;

public class BaseController {
    //    被其他控制器类继承，以便于在处理请求时返回一致的响应格式，支持 RESTful API 的设计原则
    protected ResultVO result(Object data) {
        return new ResultVO(ResultVO.SUCCESS, "success", data);
    }

    protected ResultVO result(Object data, String message) {
        return new ResultVO(ResultVO.SUCCESS, message, data);
    }

    protected ResultVO failedResult(String message) {
        return new ResultVO(ResultVO.FAIL, message, null);
    }

    protected ResultVO failedResult(String message, Object data) {
        return new ResultVO(ResultVO.FAIL, message, data);
    }

    protected boolean isNullOrEmpty(String str) {
        return "".equals(str);
    }
}
