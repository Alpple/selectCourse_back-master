package com.course.controller.student;


import com.course.config.themis.annotation.Student;
import com.course.controller.BaseController;
import com.course.model.vo.request.StudentInfoFormVO;
import com.course.model.vo.response.ResultVO;
import com.course.service.student.StudentInfoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Student
@RequestMapping("/student/info")
@RestController
public class StudentInfoController extends BaseController {
    private final StudentInfoService service;

    public StudentInfoController(StudentInfoService service) {
        this.service = service;
    }

    @GetMapping
    public ResultVO get() {
        return service.get();
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated StudentInfoFormVO formVO) {
        return service.update(formVO);
    }
}
