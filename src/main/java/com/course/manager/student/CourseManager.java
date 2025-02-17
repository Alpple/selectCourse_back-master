package com.course.manager.student;

import com.course.dao.CourseDAO;
import com.course.dao.StudentCourseDAO;
import com.course.manager.BaseManager;
import com.course.model.entity.StudentCourseEntity;
import com.course.model.vo.response.table.StudentCourseSelectedItemVO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("student_CourseManager")
public class CourseManager extends BaseManager {
    private final StudentCourseDAO studentCourseDAO;
    private final CourseDAO courseDAO;

    public CourseManager(StudentCourseDAO studentCourseDAO, CourseDAO courseDAO) {
        this.studentCourseDAO = studentCourseDAO;
        this.courseDAO = courseDAO;
    }

    //    获取学生选课信息
    public StudentCourseEntity getStudentCourseById(Integer studentCourseId) {
        return studentCourseDAO.get(studentCourseId);
    }

    //    删除学生选课记录
    @Transactional
    public int deleteStudentCourse(StudentCourseEntity studentCourseEntity) {
        courseDAO.decreaseSelectedCount(studentCourseEntity.getCourseId());
        return studentCourseDAO.delete(studentCourseEntity.getId());
    }

    //    列出学生已选课程
    public List<StudentCourseSelectedItemVO> listStudentCourseSelected(Integer studentId) {
        return studentCourseDAO.listStudentCourseSelected(studentId);
    }
}
