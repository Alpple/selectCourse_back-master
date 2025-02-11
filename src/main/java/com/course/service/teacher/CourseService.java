package com.course.service.teacher;

import com.course.manager.teacher.CourseManager;
import com.course.model.entity.CourseEntity;
import com.course.model.vo.response.ResultVO;
import com.course.model.vo.response.table.TeacherCourseItemVO;
import com.course.service.BaseService;
import com.course.util.LessonTimeConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("teacher_courseService")
public class CourseService extends BaseService {
    private final CourseManager manager;
    private final LessonTimeConverter lessonTimeConverter;

    public CourseService(CourseManager manager, LessonTimeConverter lessonTimeConverter) {
        this.manager = manager;
        this.lessonTimeConverter = lessonTimeConverter;
    }

    //    首先获取当前用户的教师id，通过manager中的 listTeacherCourse(teacherId)获取该教师的课程列表，进行遍历再进行格式转换
    public ResultVO list() {
        Integer teacherId = getUserId();

        List<TeacherCourseItemVO> list = manager.listTeacherCourse(teacherId);
        for (TeacherCourseItemVO vo : list) {
            vo.setTime(lessonTimeConverter.covertTimePart(vo.getTime()));
        }

        return result(list);
    }

    public ResultVO create(CourseEntity entity) {
        Integer teacherId = getUserId();
        entity.setTeacherId(teacherId);
        if (manager.get(entity.getId()) != null) {
            return failedResult("课程Id: " + entity.getId() + "已存在!");
        }
        manager.create(entity);
        return result("添加成功");
    }
}
