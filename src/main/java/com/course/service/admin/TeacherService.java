package com.course.service.admin;

import com.course.manager.admin.TeacherManager;
import com.course.model.entity.TeacherEntity;
import com.course.model.vo.response.ResultVO;
import com.course.service.BaseService;
import com.course.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class TeacherService extends BaseService {
    private final TeacherManager manager;
    private final UserService userService;

    public TeacherService(TeacherManager manager, UserService userService) {
        this.manager = manager;
        this.userService = userService;
    }

    public ResultVO getPageCount(String departmentName, String name) {
        return result(manager.getPageCount(departmentName, name));
    }

    public ResultVO getPage(Integer index, String departmentName, String name) {
        return result(manager.getPage(index, departmentName, name));
    }

    public ResultVO get(Integer id) {
        TeacherEntity entity = manager.get(id);
        if (entity == null) {
            return failedResult("教师Id: " + id + "不存在!");
        }

//        entity.setPassword("");

        return result(entity);
    }

    public ResultVO update(TeacherEntity entity) {
        TeacherEntity originEntity = manager.get(entity.getId());
        if (originEntity == null) {
            return failedResult("教师Id: " + entity.getId() + "不存在!");
        }
        if (manager.getDepartmentById(entity.getDepartmentId()) == null) {
            return failedResult("所属系Id: " + entity.getDepartmentId() + "不存在!");
        }


        entity.setPassword(originEntity.getPassword());

        manager.update(entity);
        return result("更新成功");
    }

    public ResultVO delete(Integer id) {
        if (manager.get(id) == null) {
            return failedResult("教师Id: " + id + "不存在!");
        }
        if (manager.hasCourse(id)) {
            return failedResult("此教师还有教授的课程未被删除");
        }

        manager.delete(id);
        return result("删除成功");
    }

    public ResultVO create(TeacherEntity entity) {
        if (manager.get(entity.getId()) != null) {
            return failedResult("教师Id: " + entity.getId() + "已存在!");
        }
        if (manager.getDepartmentById(entity.getDepartmentId()) == null) {
            return failedResult("所属系Id: " + entity.getDepartmentId() + "不存在!");
        }

        //如果管理员未设置密码，就默认设置为A12345678
        if (entity.getPassword() == null || entity.getPassword().isEmpty()) {
            entity.setPassword("A12345678");
        }

        manager.create(entity);
        return result("添加成功");
    }

    public ResultVO listName() {
        return result(manager.listName());
    }
}
