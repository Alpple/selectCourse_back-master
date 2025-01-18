package com.course.manager.admin;

import com.course.dao.DepartmentDAO;
import com.course.dao.MajorDAO;
import com.course.dao.TeacherDAO;
import com.course.manager.BaseManager;
import com.course.model.entity.DepartmentEntity;
import com.course.model.vo.response.IdNameVO;
import com.course.model.vo.response.table.DepartmentItemVO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentManager extends BaseManager {
    private final DepartmentDAO departmentDAO;
    private final TeacherDAO teacherDAO;
    private final MajorDAO majorDAO;

    public DepartmentManager(DepartmentDAO departmentDAO, TeacherDAO teacherDAO, MajorDAO majorDAO) {
        this.departmentDAO = departmentDAO;
        this.teacherDAO = teacherDAO;
        this.majorDAO = majorDAO;
    }

    //    根据学院名称数量进行分页
    public Integer getPageCount(String name) {
        int count = departmentDAO.count(name);
        return calcPageCount(count, DepartmentDAO.PAGE_SIZE);
    }

    //    根据学院名称的部分匹配进行分页查询，并返回一个包含学院信息的列表
    @Transactional
    public List<DepartmentItemVO> getPage(Integer index, String namePart) {
        List<DepartmentItemVO> departmentItemList = new ArrayList<>();
        List<DepartmentEntity> departmentList = departmentDAO.getPage(index, namePart);

        for (DepartmentEntity department : departmentList) {
            int id = department.getId();
            String name = department.getName();
            int majorCount = majorDAO.countByDepartmentId(id);
            int teacherCount = teacherDAO.countByDepartmentId(id);

            departmentItemList.add(new DepartmentItemVO(id, name, majorCount, teacherCount));
        }

        return departmentItemList;
    }

    public DepartmentEntity get(Integer id) {
        return departmentDAO.get(id);
    }

    public int create(DepartmentEntity entity) {
        return departmentDAO.insert(entity);
    }

    public int update(DepartmentEntity entity) {
        return departmentDAO.update(entity);
    }

    public int delete(Integer id) {
        return departmentDAO.delete(id);
    }

    //    检查指定学院是否有专业存在
    public boolean hasMajor(Integer id) {
        return majorDAO.countByDepartmentId(id) > 0;
    }

    //    检查指定专业是否有教师存在
    public boolean hasTeacher(Integer id) {
        return teacherDAO.countByDepartmentId(id) > 0;
    }

    //    查询所有专业的id和名称，并将其封装为IdNameVO对象列表返回
    public List<IdNameVO> listName() {
        List<IdNameVO> voList = new ArrayList<>();
        List<DepartmentEntity> entityList = departmentDAO.listName();
        for (DepartmentEntity entity : entityList) {
            voList.add(new IdNameVO(entity.getId(), entity.getName()));
        }

        return voList;
    }
}

