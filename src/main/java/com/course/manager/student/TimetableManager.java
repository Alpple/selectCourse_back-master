package com.course.manager.student;

import com.course.dao.StudentCourseDAO;
import com.course.manager.BaseManager;
import com.course.model.vo.response.table.TimetableItemVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("student_timetableManager")
public class TimetableManager extends BaseManager {
    private final StudentCourseDAO studentCourseDAO;

    public TimetableManager(StudentCourseDAO studentCourseDAO) {
        this.studentCourseDAO = studentCourseDAO;
    }

    //    获取学生课程表
    public List<TimetableItemVO> listStudentTimetable(Integer studentId) {
        return studentCourseDAO.listStudentTimetable(studentId);
    }
}
