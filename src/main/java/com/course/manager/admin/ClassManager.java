package com.course.manager.admin;

import com.course.dao.ClassDAO;
import com.course.dao.MajorDAO;
import com.course.dao.StudentDAO;
import com.course.manager.BaseManager;
import com.course.model.entity.ClassEntity;
import com.course.model.entity.MajorEntity;
import com.course.model.vo.response.IdNameVO;
import com.course.model.vo.response.table.MajorItemVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClassManager extends BaseManager {
    private final MajorDAO majorDAO;
    private final ClassDAO classDAO;
    private final StudentDAO studentDAO;

    public ClassManager(MajorDAO majorDAO, ClassDAO classDAO, StudentDAO studentDAO) {
        this.majorDAO = majorDAO;
        this.classDAO = classDAO;
        this.studentDAO = studentDAO;
    }

    //    获取分页数量
    public Integer getPageCount(String departmentName, String majorName, String name) {
        int count = classDAO.count(departmentName, majorName, name);
        return calcPageCount(count, ClassDAO.PAGE_SIZE);
    }

    //    分页查询
    public List<MajorItemVO> getPage(Integer index, String departmentName, String majorName, String name) {
        return classDAO.getPage(index, departmentName, majorName, name);
    }

    //    查询单个班级
    public ClassEntity get(Integer id) {
        return classDAO.get(id);
    }

    //    创建班级
    public int create(ClassEntity entity) {
        return classDAO.insert(entity);
    }

    //    更新班级
    public int update(ClassEntity entity) {
        return classDAO.update(entity);
    }

    //    删除班级
    public int delete(Integer id) {
        return classDAO.delete(id);
    }

    //    获取专业信息
    public MajorEntity getMajorById(Integer majorId) {
        return majorDAO.get(majorId);
    }

    //    检查班级是否有学生
    public boolean hasStudent(Integer classId) {
        return studentDAO.countByClassId(classId) > 0;
    }

    //    列出班级名称
    public List<IdNameVO> listName() {
        List<IdNameVO> voList = new ArrayList<>();
        List<ClassEntity> entityList = classDAO.listName();
        for (ClassEntity entity : entityList) {
            voList.add(new IdNameVO(entity.getId(), entity.getName()));
        }

        return voList;
    }
}
