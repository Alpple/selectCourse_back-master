package com.course.manager.teacher;

import com.course.dao.TeacherDAO;
import com.course.manager.BaseManager;
import com.course.model.entity.TeacherEntity;
import com.course.model.vo.response.TeacherInfoVO;
import org.springframework.stereotype.Component;

@Component
public class TeacherInfoManager extends BaseManager {
    private final TeacherDAO teacherDAO;

    public TeacherInfoManager(TeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

//    根据教师id查询并返回教师的详细信息
    public TeacherInfoVO getTeacherInfoById(Integer teacherId) {
        return teacherDAO.getTeacherInfoById(teacherId);
    }

    public TeacherEntity getTeacherById(Integer teacherId) {
        return teacherDAO.get(teacherId);
    }

    public int updateTeacher(TeacherEntity entity) {
        return teacherDAO.update(entity);
    }

}
