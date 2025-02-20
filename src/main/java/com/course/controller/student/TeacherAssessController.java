package com.course.controller.student;

import com.course.config.themis.annotation.NoLimit;
import com.course.config.themis.annotation.Student;
import com.course.controller.BaseController;
import com.course.model.entity.TeacherAssessEntity;
import com.course.model.vo.response.ResultVO;
import com.course.service.student.TeacherAssessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Student
@RequestMapping("/teacher/assess")
@RestController
public class TeacherAssessController extends BaseController {
    @Autowired
    private TeacherAssessService teacherAssessService;

    /**
     * 学生界面需要的评分数据
     * @return
     */
    @GetMapping("/student/page")
    @NoLimit
    public ResultVO page() {
        return teacherAssessService.queryStudentViewAssessList();
    }

    /**
     * 教师界面 需要的 评分数据
     *
     * @return
     */
    @GetMapping("/teacher/page")
    @NoLimit
    public ResultVO teacherPage(int id) {
        return teacherAssessService.queryTeacherViewAssessList(id);
    }

    @PutMapping("/save")
    @NoLimit
    public ResultVO save(TeacherAssessEntity entity) {
        return teacherAssessService.save(entity);
    }

}
