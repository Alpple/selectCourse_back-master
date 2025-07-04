package com.course.model.vo.request;

import com.course.model.constant.UserType;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoginVO {
    @NotBlank(message = "用户名不能为空 ")
    private String swnumber;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotNull(message = "必须选择用户类型")
    @Range(min = UserType.STUDENT, max = UserType.ADMIN, message = "无效的用户类型")
    private Integer userType;

    public String getSwnumber() {
        return swnumber;
    }

    public void setSwnumber(String username) {
        this.swnumber = username;
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
}
