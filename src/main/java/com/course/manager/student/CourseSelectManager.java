package com.course.manager.student;


import com.course.dao.CourseDAO;
import com.course.dao.StudentCourseDAO;
import com.course.dao.StudentDAO;
import com.course.manager.BaseManager;
import com.course.model.bo.StudentCourseSelectItemBO;
import com.course.model.entity.CourseEntity;
import com.course.model.entity.StudentCourseEntity;
import com.course.model.entity.StudentEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CourseSelectManager extends BaseManager {
    private final CourseDAO courseDAO;
    private final StudentDAO studentDAO;
    private final StudentCourseDAO studentCourseDAO;

    public CourseSelectManager(CourseDAO courseDAO, StudentDAO studentDAO, StudentCourseDAO studentCourseDAO) {
        this.courseDAO = courseDAO;
        this.studentDAO = studentDAO;
        this.studentCourseDAO = studentCourseDAO;
    }

    //    获取可选课程页面数
    public Integer getPageCount(Integer studentId, String courseName, String teacherName) {
        Integer departmentId = studentDAO.getDepartmentIdById(studentId);
        Integer grade = studentDAO.getGradeById(studentId);

        return calcPageCount(courseDAO.countStudentCanSelect(departmentId, studentId, grade, courseName, teacherName), StudentCourseDAO.PAGE_SIZE);
    }

    //    获取可选课程页面
    public List<StudentCourseSelectItemBO> getPage(Integer index, Integer studentId, String courseName, String teacherName) {
        Integer departmentId = studentDAO.getDepartmentIdById(studentId);
        Integer grade = studentDAO.getGradeById(studentId);

        return courseDAO.getStudentCanSelectPage(index,  studentId, departmentId,grade, courseName, teacherName);
    }

    //    根据课程id获取课程信息
    public CourseEntity getCourseById(Integer courseId) {
        return courseDAO.get(courseId);
    }

    //    根据学生id获取学生信息
    public StudentEntity getStudentById(Integer studentId) {
        return studentDAO.get(studentId);
    }

    //    检查学生和课程是否在同一学院
    public boolean inSameDepartment(Integer courseId, Integer studentId) {
        return courseDAO.getDepartmentIdById(courseId)
                .equals(studentDAO.getDepartmentIdById(studentId));
    }

    //    根据课程id和学生id获取学生选课信息
    public StudentCourseEntity getStudentCourseByCourseIdAndStudentId(Integer courseId, Integer studentId) {
        return studentCourseDAO.getByCourseIdAndStudentId(courseId, studentId);
    }

    //    获取学生年级
    public Integer getStudentGradeById(Integer studentId) {
        return studentDAO.getGradeById(studentId);
    }


    //    创建学生选课记录
    @Transactional
    public int create(StudentCourseEntity entity) {
        courseDAO.increaseSelectedCount(entity.getCourseId());
        return studentCourseDAO.insert(entity);
    }

    //    按时间段统计学生选课数量
    public int countStudentCourseSelectedByTimePart(Integer studentId, String timePart) {
        return studentCourseDAO.countStudentCourseSelectedByTimePart(studentId, timePart);
    }
}
