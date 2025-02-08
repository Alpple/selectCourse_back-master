package com.course.controller.teacher;

import com.course.config.themis.annotation.Teacher;
import com.course.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Teacher
@RequestMapping("/teacher/info")
@RestController
public class CourseController extends BaseController {

}
