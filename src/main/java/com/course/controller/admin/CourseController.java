package com.course.controller.admin;

import com.course.config.themis.annotation.Admin;
import com.course.controller.BaseController;
import com.course.model.vo.response.ResultVO;
import com.course.service.admin.CourseService;
import org.springframework.web.bind.annotation.*;

@Admin(Admin.COURSE_MANAGE)
@RequestMapping("/admin/course")
@RestController
public class CourseController extends BaseController {
    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResultVO get(@PathVariable Integer id) {
        return service.get(id);
    }


    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @RequestMapping("/page/count")
    public ResultVO getPageCount(String departmentName, String teacherName, String name) {
        return service.getPageCount(departmentName, teacherName, name);
    }

    @RequestMapping("/page")
    public ResultVO getPage(String departmentName, String teacherName, String name) {
        return service.getPage(1, departmentName, teacherName, name);
    }

    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, String departmentName, String teacherName, String name) {
        return service.getPage(index, departmentName, teacherName, name);
    }

    @Admin
    @RequestMapping("/names")
    public ResultVO listName() {
        return service.listName();
    }
}
