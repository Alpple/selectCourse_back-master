package com.course.config.aop;

import com.course.model.constant.HttpStatusCode;
import com.course.model.vo.response.ResultVO;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
public class ResultFailedCodeAspect {
    //    aop拦截,定义与控制器方法的返回值相关的切面逻辑
    //    切点定义
    @Pointcut("execution(public com.course.model.vo.response.ResultVO " +
            "com.course.controller..*.*(..))")
    public void controllerResult() {
    }

    //    后置返回通知
    //    对控制器方法返回值进行统一处理的场景，比如在遇到错误时自动设置适当的 HTTP 状态码
    @AfterReturning(value = "controllerResult()", returning = "result")
    public Object afterReturning(ResultVO result) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return result;
        }
        HttpServletResponse response = requestAttributes.getResponse();
        if (response == null) {
            return result;
        }

        if (result.getCode() == ResultVO.FAIL) {
            response.setStatus(HttpStatusCode.NOT_ACCEPTABLE);
        }

        return result;
    }
}
