package com.course.manager.teacher;


import com.course.dao.CourseDAO;
import com.course.dao.StudentCourseDAO;
import com.course.manager.BaseManager;
import com.course.model.entity.CourseEntity;
import com.course.model.entity.StudentCourseEntity;
import com.course.model.vo.response.table.TeacherGradeItemVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GradeManager extends BaseManager {
    private final CourseDAO courseDAO;
    private final StudentCourseDAO studentCourseDAO;

    public GradeManager(CourseDAO courseDAO, StudentCourseDAO studentCourseDAO) {
        this.courseDAO = courseDAO;
        this.studentCourseDAO = studentCourseDAO;
    }

    //    获取教师成绩页面数
    public Integer getTeacherGradePageCount(Integer teacherId, String courseName, String studentName) {
        return calcPageCount(
                studentCourseDAO.countTeacherGrade(teacherId, courseName, studentName),
                StudentCourseDAO.PAGE_SIZE);
    }

    //    获取教师成绩页面
    public List<TeacherGradeItemVO> getTeacherGradePage(Integer index, Integer teacherId, String courseName, String studentName) {
        return studentCourseDAO.getTeacherGradePage(index, teacherId, courseName, studentName);
    }

    //    根据学生课程id获取学生课程信息
    public StudentCourseEntity getStudentCourseById(Integer studentCourseId) {
        return studentCourseDAO.get(studentCourseId);
    }

    //    根据课程id获取课程信息
    public CourseEntity getCourseById(Integer courseId) {
        return courseDAO.get(courseId);
    }

    public int updateStudentCourse(StudentCourseEntity entity) {
        return studentCourseDAO.update(entity);
    }
}
