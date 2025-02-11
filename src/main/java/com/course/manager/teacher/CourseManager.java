package com.course.manager.teacher;


import com.course.dao.CourseDAO;
import com.course.dao.TeacherDAO;
import com.course.manager.BaseManager;
import com.course.model.entity.CourseEntity;
import com.course.model.vo.response.table.TeacherCourseItemVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("teacher_CourseManager")
public class CourseManager extends BaseManager {
    private final TeacherDAO teacherDAO;
    private final CourseDAO courseDAO;

    public CourseManager(TeacherDAO teacherDAO, CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
        this.teacherDAO = teacherDAO;
    }


    //    通过教师id获取与该教师相关的课程列表
    public List<TeacherCourseItemVO> listTeacherCourse(Integer teacherId) {
        return teacherDAO.listTeacherCourse(teacherId);
    }

    public CourseEntity get(Integer id) {
        return courseDAO.get(id);
    }

    public int create(CourseEntity entity) {
        return courseDAO.insert(entity);
    }
}
