package com.course.controller.admin;

import com.course.config.themis.annotation.Admin;
import com.course.config.themis.annotation.NoLimit;
import com.course.controller.BaseController;
import com.course.model.entity.ClassEntity;
import com.course.model.vo.response.ResultVO;
import com.course.service.admin.ClassService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Admin(Admin.CLASS_MANAGE)
@RequestMapping("/admin/class")
@RestController
public class ClassController extends BaseController {
    private final ClassService service;

    public ClassController(ClassService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResultVO get(@PathVariable Integer id) {
        return service.get(id);
    }

    @PostMapping
    public ResultVO create(@RequestBody @Validated ClassEntity entity) {
        return service.create(entity);
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated ClassEntity entity) {
        return service.update(entity);
    }

    @RequestMapping("/page/count")
    public ResultVO getPageCount(String departmentName, String majorName, String name) {
        return service.getPageCount(departmentName, majorName, name);
    }

    @RequestMapping("/page")
    public ResultVO getPage(String departmentName, String majorName, String name) {
        return service.getPage(1, departmentName, majorName, name);
    }

    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, String departmentName, String majorName, String name) {
        return service.getPage(index, departmentName, majorName, name);
    }

    @NoLimit
    @RequestMapping("/names")
    public ResultVO listName() {
        return service.listName();
    }
}
