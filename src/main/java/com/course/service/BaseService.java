package com.course.service;

import com.course.manager.LoginStatusManager;
import com.course.model.bo.LoginStatusBO;
import com.course.model.vo.response.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

public class BaseService {
    //    提供服务层的基础功能，例如获取用户登录状态、构建 API 响应结果
    //    可以直接解析出当前请求对象的ID
    @Autowired
    private HttpSession session;  // 用于管理用户的会话信息
    @Autowired
    private LoginStatusManager loginStatusManager;  // 用于管理用户的登录状态

    private LoginStatusBO getLoginStatus() {
        return loginStatusManager.getLoginStatus(session);
    }

    protected Integer getUserId() {
        return getLoginStatus().getUserId();
    }

    protected ResultVO result(Object data) {
        return new ResultVO(ResultVO.SUCCESS, "success", data);
    }

    protected ResultVO result(Object data, String message) {
        return new ResultVO(ResultVO.SUCCESS, message, data);
    }

    protected ResultVO failedResult(String message) {
        return new ResultVO(ResultVO.SUCCESS, message, null);
    }

    protected ResultVO failedResult(String message, Object data) {
        return new ResultVO(ResultVO.FAIL, message, data);
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public LoginStatusManager getLoginStatusManager() {
        return loginStatusManager;
    }

    public void setLoginStatusManager(LoginStatusManager loginStatusManager) {
        this.loginStatusManager = loginStatusManager;
    }
}
