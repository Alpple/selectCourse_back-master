package com.course.service.student;

import com.course.manager.student.CourseManager;
import com.course.model.entity.StudentCourseEntity;
import com.course.model.vo.response.ResultVO;
import com.course.service.BaseService;
import org.springframework.stereotype.Service;

@Service("student_courseService")
public class CourseService extends BaseService {
    private final CourseManager manager;

    public CourseService(CourseManager manager) {
        this.manager = manager;
    }

    //    列出已选课程
    public ResultVO list() {
        Integer studentId = getUserId();
        return result(manager.listStudentCourseSelected(studentId));
    }

    //    删除选课记录
    public ResultVO delete(Integer studentCourseId) {
        Integer studentId = getUserId();
        StudentCourseEntity studentCourse = manager.getStudentCourseById(studentCourseId);

        if (studentCourse == null) {
            return failedResult("学生选课id:" + studentCourseId + "不存在");
        }
        if (!studentCourse.getStudentId().equals(studentId)) {
            return failedResult("此课程非此学生所选!");
        }
        if (studentCourse.getDailyScore() != null ||
                studentCourse.getExamScore() != null ||
                studentCourse.getScore() != null) {
            return failedResult("学生已获得成绩,不能退选");
        }

        manager.deleteStudentCourse(studentCourse);
        return result("退选成功");
    }
}
