package com.course.model.vo.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import java.util.Date;

@Data
public class TeacherInfoFormVO {
    @Length(min = 8, max = 12, message = "密码必须为8~12位的数字、字母或特殊符号的混合形式")
    private String password;

    @Email(message = "邮箱格式不正确")
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @Range(min = 0, max = 1)
    private Integer sex;

    public @Length(min = 8, max = 12, message = "密码必须为8~12位的数字、字母或特殊符号的混合形式") String getPassword() {
        return password;
    }

    public void setPassword(@Length(min = 8, max = 12, message = "密码必须为8~12位的数字、字母或特殊符号的混合形式") String password) {
        this.password = password;
    }

    public @Email(message = "邮箱格式不正确") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "邮箱格式不正确") String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public @Range(min = 0, max = 1) Integer getSex() {
        return sex;
    }

    public void setSex(@Range(min = 0, max = 1) Integer sex) {
        this.sex = sex;
    }
}
