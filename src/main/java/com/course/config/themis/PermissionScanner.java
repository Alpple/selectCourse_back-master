package com.course.config.themis;

import com.course.config.themis.annotation.*;
import com.course.model.constant.UserType;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Component
public class PermissionScanner {
    public Permission scan(Method method) {
        Admin annotation;
        if (getAnnotation(method, NoLimit.class) != null) {
            return new Permission(false);
        } else if(getAnnotation(method, Login.class) != null) {
            return new Permission(UserType.NO,0);
        } else if(getAnnotation(method, Student.class) != null) {
            return new Permission(UserType.STUDENT);
        } else if(getAnnotation(method, Teacher.class) != null) {
            return new Permission(UserType.TEACHER);
        } else if((annotation = getAnnotation(method,Admin.class)) != null) {
            return new Permission(UserType.ADMIN,annotation.value());
        }

        return new Permission(false);
    }

    //    获取给定方法上的指定注解，如果该方法上没有找到该注解，则继续查找该方法所在类上的同类型注解
    private <T extends Annotation> T getAnnotation(Method method, Class<T> annotationClass) {
        T annotation = method.getAnnotation(annotationClass);
        if (annotation == null) {
            annotation = method.getDeclaringClass().getDeclaredAnnotation(annotationClass);
        }

        return annotation;
    }
}
