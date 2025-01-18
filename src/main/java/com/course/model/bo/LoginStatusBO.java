package com.course.model.bo;

import com.course.model.constant.UserType;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class LoginStatusBO implements Serializable {
    //    表示用户的登录状态对象
    private Boolean loggedIn = false;
    private Integer userId;
    private String swnumber;
    private Integer userType = UserType.NO;
    private Integer permission = 0;

    //    在用户登录时创建此对象，并将其存储在会话中，以便在后续请求中跟踪用户的登录状态和权限
    public static LoginStatusBO fromAuthInfo(AuthInfoBO authInfoBO) {
        LoginStatusBO loginStatus = new LoginStatusBO();
        loginStatus.loggedIn = true;
        loginStatus.userId = authInfoBO.getId();
        loginStatus.swnumber = authInfoBO.getSwnumber();
        loginStatus.userType = authInfoBO.getUserType();
        loginStatus.permission = authInfoBO.getPermission();

        return loginStatus;
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSwnumber() {
        return swnumber;
    }

    public void setSwnumber(String swnumber) {
        this.swnumber = swnumber;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

}
