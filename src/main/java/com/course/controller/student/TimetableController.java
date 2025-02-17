package com.course.controller.student;

import com.course.config.themis.annotation.Student;
import com.course.controller.BaseController;
import com.course.model.vo.response.ResultVO;
import com.course.service.student.TimetableService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Student
@RequestMapping("/student/timetable")
@RestController("student_timeTableController")
public class TimetableController extends BaseController {
    private final TimetableService service;

    public TimetableController(TimetableService service) {
        this.service = service;
    }

    @RequestMapping
    public ResultVO get() {
        return service.get();
    }
}
