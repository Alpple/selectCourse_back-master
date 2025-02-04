package com.course.manager.admin;

import com.course.dao.ClassDAO;
import com.course.dao.StudentCourseDAO;
import com.course.dao.StudentDAO;
import com.course.manager.BaseManager;
import com.course.model.entity.ClassEntity;
import com.course.model.entity.StudentEntity;
import com.course.model.vo.response.IdNameVO;
import com.course.model.vo.response.table.StudentItemVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentManager extends BaseManager {
    private final ClassDAO classDAO;
    private final StudentCourseDAO studentCourseDAO;
    private final StudentDAO studentDAO;

    public StudentManager(ClassDAO classDAO, StudentCourseDAO studentCourseDAO, StudentDAO studentDAO) {
        this.classDAO = classDAO;
        this.studentCourseDAO = studentCourseDAO;
        this.studentDAO = studentDAO;
    }

    //    获取分页数量
    public Integer getPageCount(String majorName, String className, String name) {
        int count = studentDAO.count(majorName, className, name);
        return calcPageCount(count, StudentDAO.PAGE_SIZE);
    }

    //    获取分页学生数据
    public List<StudentItemVO> getPage(Integer index, String majorName, String className, String name) {
        return studentDAO.getPage(index, majorName, className, name);
    }

    public StudentEntity get(Integer id) {
        return studentDAO.get(id);
    }

    public int create(StudentEntity entity) {
        return studentDAO.insert(entity);
    }

    public int update(StudentEntity entity) {
        return studentDAO.update(entity);
    }

    public int delete(Integer id) {
        return studentDAO.delete(id);
    }

    //    根据班级id获取班级信息
    public ClassEntity getClassById(Integer classId) {
        return classDAO.get(classId);
    }

    //    检查学生是否有选课记录
    public boolean hasStudentCourse(Integer studentId) {
        return studentCourseDAO.countByCourseId(studentId) > 0;
    }

    //    列出学生名称
    public List<IdNameVO> listName() {
        List<IdNameVO> voList = new ArrayList<>();
        List<StudentEntity> entityList = studentDAO.listName();
        for (StudentEntity entity : entityList) {
            voList.add(new IdNameVO(entity.getId(), entity.getName()));
        }

        return voList;
    }
}
