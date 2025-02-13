package com.course.controller.teacher;

import com.course.config.themis.annotation.Teacher;
import com.course.controller.BaseController;
import com.course.model.vo.request.TeacherInfoFormVO;
import com.course.model.vo.response.ResultVO;
import com.course.service.teacher.TeacherInfoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Teacher
@RequestMapping("/teacher/info")
@RestController
public class TeacherInfoController extends BaseController {
    private final TeacherInfoService service;

    public TeacherInfoController(TeacherInfoService service) {
        this.service = service;
    }

    @GetMapping
    public ResultVO get() {
        return service.get();
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated TeacherInfoFormVO formVO) {
        return service.update(formVO);
    }
}
