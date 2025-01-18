package com.course.service.admin;

import com.course.manager.admin.DepartmentManager;
import com.course.model.entity.DepartmentEntity;
import com.course.model.vo.response.ResultVO;
import com.course.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService extends BaseService {
    private final DepartmentManager manager;

    public DepartmentService(DepartmentManager manager) {
        this.manager = manager;
    }

    public ResultVO getPageCount(String name) {
        return result(manager.getPageCount(name));
    }

    public ResultVO getPage(Integer index, String name) {
        return result(manager.getPage(index, name));
    }

    public ResultVO get(Integer id) {
        DepartmentEntity entity = manager.get(id);
        if (entity == null) {
            return failedResult("学院id" + id + "不存在！");
        }

        return result(entity);
    }

    public ResultVO update(DepartmentEntity entity) {
        if (manager.get(entity.getId()) == null) {
            return failedResult("学院id" + entity.getId() + "不存在！");
        }

        manager.update(entity);
        return result("更新成功");
    }

    public ResultVO delete(Integer id) {
        if (manager.get(id) == null) {
            return failedResult("学院id" + id + "不存在！");
        }
        if (manager.hasMajor(id)) {
            return failedResult("此学院中还有专业未被删除");
        }
        if (manager.hasTeacher(id)) {
            return failedResult("此学院中还有教师未删除");
        }

        manager.delete(id);
        return result("删除成功");
    }

    public ResultVO create(DepartmentEntity entity) {
        if (manager.get(entity.getId()) != null) {
            return failedResult("学院id" + entity.getId() + "已存在！");
        }

        manager.create(entity);
        return result("添加成功");
    }

    //    获取所有专业的id和名称，并返回结果
    public ResultVO listName() {
        return result(manager.listName());
    }
}
