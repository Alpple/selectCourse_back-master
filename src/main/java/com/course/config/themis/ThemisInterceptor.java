package com.course.config.themis;

import com.course.manager.LoginStatusManager;
import com.course.model.bo.LoginStatusBO;
import com.course.model.constant.HttpStatusCode;
import com.course.model.vo.response.ResultVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Method;


@Component
public class ThemisInterceptor implements HandlerInterceptor {
    //Spring MVC 的拦截器，用于处理请求的权限验证和登录状态检查
    private final PermissionScanner scanner;    // PermissionScanner: 用于扫描方法的权限注解
    private final LoginStatusManager loginStatusManager;  // LoginStatusManager: 管理用户的登录状态
    private final ObjectMapper objectMapper;  // 用于将对象转换为 JSON 格式

    public ThemisInterceptor(PermissionScanner scanner, LoginStatusManager loginStatusManager, ObjectMapper objectMapper) {
        this.scanner = scanner;
        this.loginStatusManager = loginStatusManager;
        this.objectMapper = objectMapper;
    }

    //    请求处理之前被调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        Permission permission = scanner.scan(method);
        if (!permission.getNeedLogin() || permission.getUserType().equals(0)) {
            return true;
        }

        LoginStatusBO loginStatus = loginStatusManager.getLoginStatus(request.getSession());
        if (!loginStatus.getLoggedIn()) {
            noLogin(response);
            return false;
        }

        if (!loginStatus.getUserType().equals(permission.getUserType())) {
            errorRole(response);
            return false;
        }

        return true;
    }

    private void noLogin(HttpServletResponse response) {
        response.setStatus(HttpStatusCode.UNAUTHORIZED);
        ResultVO resultVO = new ResultVO(ResultVO.NO_LOGIN, "您没有登录", null);
        sendResult(resultVO, response);
    }

    private void errorRole(HttpServletResponse response) {
        response.setStatus(HttpStatusCode.FORBIDDEN);
        ResultVO resultVO = new ResultVO(ResultVO.ERROR_ROLE, "您的身份错误", null);
        sendResult(resultVO, response);
    }

    private void noPermission(HttpServletResponse response) {
        response.setStatus(HttpStatusCode.FORBIDDEN);
        ResultVO resultVO = new ResultVO(ResultVO.NO_PERMISSION, "您没有此权限", null);
        sendResult(resultVO, response);
    }

    private void sendResult(ResultVO result, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        try (Writer writer = response.getWriter()) {
            writer.write(objectMapper.writeValueAsString(result));
            writer.flush();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
