package com.course.model.bo;

import com.course.model.constant.UserType;
import com.course.model.entity.AdminEntity;
import com.course.model.entity.StudentEntity;
import com.course.model.entity.TeacherEntity;
import lombok.Data;

@Data
public class AuthInfoBO {
    //    封装了用户的认证信息，包括用户ID、工号、密码、用户类型和权限等，主要用于身份验证和管理
    private Integer id;
    private String swnumber;
    private String password;
    private Integer userType;
    private Integer permission = 0;

    public AuthInfoBO() {

    }

    private AuthInfoBO(Integer id, String swnumber, String password, Integer userType) {
        this(id, swnumber, password, userType, 0);
    }

    private AuthInfoBO(Integer id, String swnumber, String password, Integer userType, Integer permission) {
        this.id = id;
        this.swnumber = swnumber;
        this.password = password;
        this.userType = userType;
        this.permission = permission;
    }

    //    在用户登录或身份验证过程中，可以通过此类来表示用户信息，并在不同的实体（如学生、教师、管理员）之间进行转换
    //    通过静态工厂方法，根据不同的实体类型创建相应的认证信息对象

    public static AuthInfoBO fromStudent(StudentEntity entity) {
        if (entity == null) {
            return null;
        }
        return new AuthInfoBO(entity.getId(), entity.getNumber(), entity.getPassword(), UserType.STUDENT);
    }

    public static AuthInfoBO fromTeacher(TeacherEntity entity) {
        if (entity == null) {
            return null;
        }
        return new AuthInfoBO(entity.getId(), entity.getNumber(), entity.getPassword(), UserType.TEACHER);
    }

    public static AuthInfoBO fromAdmin(AdminEntity entity) {
        if (entity == null) {
            return null;
        }
        return new AuthInfoBO(entity.getId(), entity.getUsername(), entity.getPassword(), UserType.ADMIN,
                entity.getPrivilege());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSwnumber() {
        return swnumber;
    }

    public void setSwnumber(String swnumber) {
        this.swnumber = swnumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
