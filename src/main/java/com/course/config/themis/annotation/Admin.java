package com.course.config.themis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义了一个自定义注解 @Admin，用于权限管理
 */
//指定该注解可以应用于哪些 Java 元素
@Target({ElementType.TYPE, ElementType.METHOD})
//定义注解的生命周期，这里设置为 RUNTIME，表示注解在运行时可用
@Retention(RetentionPolicy.RUNTIME)
public @interface Admin {
    // 无权限
    int NO = 0;
    // 学院管理
    int DEPARTMENT_MANAGE = 1;
    // 专业管理
    int MAJOR_MANAGE = 2;
    // 班级管理
    int CLASS_MANAGE = 4;
    // 学生管理
    int STUDENT_MANAGE = 8;
    // 教师管理
    int TEACHER_MANAGE = 16;
    // 课程管理
    int COURSE_MANAGE = 32;
    // 学生选课管理
    int STUDENT_COURSE_MANAGE = 64;
    // 管理员管理
    int ADMIN_MANAGE = 128;
    // 所有权限
    int ALL = 255;

    int value() default 0;
}
