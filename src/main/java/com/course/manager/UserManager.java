package com.course.manager;

import com.course.dao.AdminDAO;
import com.course.dao.StudentDAO;
import com.course.dao.TeacherDAO;
import com.course.model.bo.AuthInfoBO;
import com.course.model.constant.UserType;
import com.course.model.entity.StudentEntity;
import org.springframework.stereotype.Component;

@Component
public class UserManager extends BaseManager {
    //    管理用户的相关操作，包括获取身份信息和更新学生的最后登录时间
    private final AdminDAO adminDAO;
    private final TeacherDAO teacherDAO;
    private StudentDAO studentDAO;

    public UserManager(AdminDAO adminDAO, TeacherDAO teacherDAO, StudentDAO studentDAO) {
        this.adminDAO = adminDAO;
        this.teacherDAO = teacherDAO;
        this.studentDAO = studentDAO;
    }

    //    获取身份信息
    public AuthInfoBO getAuthInfoBySwnumber(String swnumber, Integer userType) {
        if (userType == UserType.STUDENT) {
            return AuthInfoBO.fromStudent(studentDAO.getByNumber(swnumber));
        } else if (userType == UserType.TEACHER) {
            return AuthInfoBO.fromTeacher(teacherDAO.getByNumber(swnumber));
        } else if (userType == UserType.ADMIN) {
            return AuthInfoBO.fromAdmin(adminDAO.getByUsername(swnumber));
        }

        return null;
    }

    //    更新学生最后登录时间
    public void updateStudentLastLoginTime(String number) {
        StudentEntity entity = studentDAO.getByNumber(number);
        if (entity != null) {
            return;
        }

        studentDAO.update(entity);
    }
}
