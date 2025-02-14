package com.course.service.teacher;

import com.course.manager.teacher.GradeManager;
import com.course.model.entity.StudentCourseEntity;
import com.course.model.vo.response.ResultVO;
import com.course.model.vo.response.TeacherGradeVO;
import com.course.service.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class GradeService extends BaseService {
    private final GradeManager manager;

    public GradeService(GradeManager manager) {
        this.manager = manager;
    }

    //    获取成绩页面数
    public ResultVO getPageCount(String courseName, String studentName) {
        Integer teacherId = getUserId();
        return result(manager.getTeacherGradePageCount(teacherId, courseName, studentName));
    }

    //    获取成绩页面
    public ResultVO getPage(Integer index, String courseName, String studentName) {
        Integer teacherId = getUserId();
        return result(manager.getTeacherGradePage(teacherId, index, courseName, studentName));
    }

    //    更新学生课程成绩
    public ResultVO update(TeacherGradeVO vo) {
        Integer teacherId = getUserId();
        StudentCourseEntity studentCourse = manager.getStudentCourseById(vo.getStudentCourseId());

        if (studentCourse == null) {
            return failedResult("学生选课id：" + vo.getStudentCourseId() + "不存在");
        }
        if (!manager.getCourseById(studentCourse.getCourseId()).getTeacherId().equals(teacherId)) {
            return failedResult("此课程非您教授");
        }

        BeanUtils.copyProperties(vo, studentCourse);
        manager.updateStudentCourse(studentCourse);
        return result("打分成功");
    }

    public ResultVO get(Integer studentCourseId) {
        Integer teacherId = getUserId();
        StudentCourseEntity studentCourse = manager.getStudentCourseById(studentCourseId);

        if (studentCourse == null) {
            return failedResult("学生选课id："+ studentCourseId+"不存在");
        }
        if (!manager.getCourseById(studentCourse.getCourseId()).getTeacherId().equals(teacherId)) {
            return failedResult("此课程非您教授");
        }

        TeacherGradeVO vo = new TeacherGradeVO();
        BeanUtils.copyProperties(studentCourse, vo);
        vo.setStudentCourseId(studentCourseId);

        return result(vo);
    }
}
