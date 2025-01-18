package com.course.manager;

import com.course.model.bo.LoginStatusBO;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class LoginStatusManager extends BaseManager {
    //    管理用户的登录状态
    private static final String SESSION_USER_STATUS = "user_status";

    //    通过 HttpSession 存储和检索 LoginStatusBO 对象
    //    用来跟踪用户登录状态，比如是否已登录，用户角色等信息
    public void setLoginStatus(HttpSession session, LoginStatusBO loginStatus) {
        session.setAttribute(SESSION_USER_STATUS, loginStatus);
    }

    public LoginStatusBO getLoginStatus(HttpSession session) {
        LoginStatusBO loginStatus = (LoginStatusBO) session.getAttribute(SESSION_USER_STATUS);
        if (loginStatus == null) {
            loginStatus = new LoginStatusBO();
            setLoginStatus(session, loginStatus);
        }
        return loginStatus;
    }
}
