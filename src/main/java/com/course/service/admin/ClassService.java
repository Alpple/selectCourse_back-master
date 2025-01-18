package com.course.service.admin;

import com.course.manager.admin.ClassManager;
import com.course.model.entity.ClassEntity;
import com.course.model.vo.response.ResultVO;
import com.course.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class ClassService extends BaseService {
    private final ClassManager manager;

    public ClassService(ClassManager manager) {
        this.manager = manager;
    }

    //    获取分页数量
    public ResultVO getPageCount(String departmentName, String majorName, String name) {
        return result(manager.getPageCount(departmentName, majorName, name));
    }

    //    获取分页数据
    public ResultVO getPage(Integer index, String departmentName, String majorName, String name) {
        return result(manager.getPage(index, departmentName, majorName, name));
    }

    //    获取单个班级
    public ResultVO get(Integer id) {
        ClassEntity entity = manager.get(id);
        if (entity == null) {
            return failedResult("班级Id: " + id + "不存在!");
        }

        return result(entity);
    }


    public ResultVO update(ClassEntity entity) {
        if (manager.get(entity.getId()) == null) {
            return failedResult("班级Id: " + entity.getId() + "不存在!");
        }
        if (manager.getMajorById(entity.getMajorId()) == null) {
            return failedResult("所属专业Id: " + entity.getMajorId() + "不存在!");
        }

        manager.update(entity);
        return result("更新成功");
    }

    public ResultVO delete(Integer id) {
        if (manager.get(id) == null) {
            return failedResult("班级Id: " + id + "不存在!");
        }
        if (manager.hasStudent(id)) {
            return failedResult("此班级中还有学生未被删除");
        }

        manager.delete(id);
        return result("删除成功");
    }

    public ResultVO create(ClassEntity entity) {
        if (manager.get(entity.getId()) != null) {
            return failedResult("班级Id: " + entity.getId() + "已存在!");
        }
        if (manager.getMajorById(entity.getMajorId()) == null) {
            return failedResult("所属专业Id: " + entity.getMajorId() + "不存在!");
        }

        manager.create(entity);
        return result("添加成功");
    }

    //    列出班级名称
    public ResultVO listName() {
        return result(manager.listName());
    }
}
