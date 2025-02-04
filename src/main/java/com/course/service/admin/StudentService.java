package com.course.service.admin;

import com.course.manager.admin.StudentManager;
import com.course.model.entity.StudentEntity;
import com.course.model.vo.response.ResultVO;
import com.course.service.BaseService;
import com.course.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends BaseService {
    private final StudentManager manager;
    private final UserService userService;

    public StudentService(StudentManager manager, UserService userService) {
        this.manager = manager;
        this.userService = userService;
    }

    //    获取分页数量
    public ResultVO getPageCount(String majorName, String className, String name) {
        return result(manager.getPageCount(majorName, className, name));
    }

    //    获取分页学生数据
    public ResultVO getPage(Integer index, String majorName, String className, String name) {
        return result(manager.getPage(index, majorName, className, name));
    }

    //    获取单个学生信息
    public ResultVO get(Integer id) {
        StudentEntity entity = manager.get(id);
        if (entity == null) {
            return failedResult("学生id:" + id + "不存在!");
        }
//    entity.setPassword("");

        return result(entity);
    }

    //    更新学生信息
    public ResultVO update(StudentEntity entity) {
        StudentEntity origin = manager.get(entity.getId());
        if (origin == null) {
            return failedResult("学生id:" + entity.getId() + "不存在!");
        }
        if (manager.getClassById(entity.getClassId()) == null) {
            return failedResult("所属班级id:" + entity.getClassId() + "不存在!");
        }
        entity.setPassword(origin.getPassword());

        manager.update(entity);
        return result("更新成功");
    }

    public ResultVO delete(Integer id) {
        if (manager.get(id) == null) {
            return failedResult("学生id:"+id+"不存在!");
        }
        if (manager.hasStudentCourse(id)) {
            return failedResult("此学生还有未退选课程");
        }

        manager.delete(id);
        return result("删除成功");
    }

    public ResultVO create(StudentEntity entity) {
        if (manager.get(entity.getId()) != null) {
            return failedResult("学生id:"+entity.getId()+"已存在!");
        }
        if (manager.getClassById(entity.getClassId()) == null) {
            return failedResult("所属班级id:"+entity.getClassId()+"不存在!");
        }

//        如果管理员未设置密码,就默认设置为A12345678
        if (entity.getPassword().equals("")) {
            entity.setPassword("A12345678");
        }

        manager.create(entity);
        return result("添加成功");
    }

    public ResultVO listName() {
        return result(manager.listName());
    }
}
