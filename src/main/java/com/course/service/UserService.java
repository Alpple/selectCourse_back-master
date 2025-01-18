package com.course.service;

import com.course.manager.LoginStatusManager;
import com.course.manager.UserManager;
import com.course.model.bo.AuthInfoBO;
import com.course.model.bo.LoginStatusBO;
import com.course.model.constant.UserType;
import com.course.model.vo.response.ResultVO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserService extends BaseService {
    //    用户管理的服务，包括登录、获取登录状态和注销功能
    private final HttpSession session;
    private final UserManager manager;
    private final LoginStatusManager loginStatusManager;

    public UserService(HttpSession session, UserManager manager, LoginStatusManager loginStatusManager) {
        this.session = session;
        this.manager = manager;
        this.loginStatusManager = loginStatusManager;
    }

    //    登录
    public ResultVO login(String swnumber, String password, Integer userType) {
        AuthInfoBO authInfo = manager.getAuthInfoBySwnumber(swnumber, userType);
        if (authInfo == null) {
            return failedResult("用户不存在");
        }
        if (!password.equals(authInfo.getPassword())) {
            return failedResult("密码错误");
        }

        if (authInfo.getUserType().equals(UserType.STUDENT)) {
            manager.updateStudentLastLoginTime(swnumber);
        }

        LoginStatusBO statusBO = LoginStatusBO.fromAuthInfo(authInfo);
        loginStatusManager.setLoginStatus(session, statusBO);

        return result(statusBO);
    }

    //    获取登录状态
    public ResultVO getLoginStatus() {
        LoginStatusBO loginStatusBO = loginStatusManager.getLoginStatus(session);
        return result(loginStatusBO);
    }

    //    注销
    public ResultVO logout() {
        loginStatusManager.setLoginStatus(session, null);
        return result("注销成功");
    }
}
