package com.course.manager.admin;

import com.course.dao.ClassDAO;
import com.course.dao.DepartmentDAO;
import com.course.dao.MajorDAO;
import com.course.manager.BaseManager;
import com.course.model.entity.DepartmentEntity;
import com.course.model.entity.MajorEntity;
import com.course.model.vo.response.IdNameVO;
import com.course.model.vo.response.table.MajorItemVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MajorManager extends BaseManager {
    private final MajorDAO majorDAO;
    private final DepartmentDAO departmentDAO;
    private final ClassDAO classDAO;

    public MajorManager(MajorDAO majorDAO, DepartmentDAO departmentDAO, ClassDAO classDAO) {
        this.majorDAO = majorDAO;
        this.departmentDAO = departmentDAO;
        this.classDAO = classDAO;
    }

    //    获取分页数量
    public Integer getPageCount(String departmentName, String name) {
        int count = majorDAO.count(departmentName, name);
        return calcPageCount(count, MajorDAO.PAGE_SIZE);
    }

    //    分页查询方法
    public List<MajorItemVO> getPage(Integer index, String departmentName, String name) {
        return majorDAO.getPage(index, departmentName, name);
    }

    public MajorEntity get(Integer id) {
        return majorDAO.get(id);
    }

    public int create(MajorEntity entity) {
        return majorDAO.insert(entity);
    }

    public int update(MajorEntity entity) {
        return majorDAO.update(entity);
    }

    public int delete(Integer id) {
        return majorDAO.delete(id);
    }

    //    检查指定专业是否由班级存在
    public boolean hasClass(Integer majorId) {
        return classDAO.countByMajorId(majorId) > 0;
    }

    //    根据学院id获取对应的学院实体
    public DepartmentEntity getDepartmentById(Integer id) {
        return departmentDAO.get(id);
    }

    //    查询所有专业的id和名称,并将其以IdNameVO的形式返回
    public List<IdNameVO> listName() {
        List<IdNameVO> voList = new ArrayList<>();
        List<MajorEntity> entityList = majorDAO.listName();
        for (MajorEntity entity : entityList) {
            voList.add(new IdNameVO(entity.getId(), entity.getName()));
        }

        return voList;
    }
}
