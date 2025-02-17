package com.course.manager.student;

import com.course.dao.StudentDAO;
import com.course.manager.BaseManager;
import com.course.model.entity.StudentEntity;
import com.course.model.vo.response.StudentInfoVO;
import org.springframework.stereotype.Component;

@Component
public class StudentInfoManager extends BaseManager {
    private final StudentDAO studentDAO;

    public StudentInfoManager(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public StudentInfoVO getStudentInfoByStudentId(Integer studentId) {
        return studentDAO.getStudentInfoById(studentId);
    }

    public StudentEntity getStudentById(Integer studentId) {
        return studentDAO.get(studentId);
    }

    public int updateStudent(StudentEntity entity) {
        return studentDAO.update(entity);
    }
}
