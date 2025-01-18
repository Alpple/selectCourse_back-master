package com.course.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.course.dao.mapper.StudentMapper;
import com.course.model.entity.StudentEntity;
import com.course.model.vo.response.StudentInfoVO;
import com.course.model.vo.response.table.StudentItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAO extends BaseDAO {
    public static final int PAGE_SIZE = 20;
    private final StudentMapper mapper;

    public StudentDAO(StudentMapper mapper) {
        this.mapper = mapper;
    }

    //    根据学生学号查学生实体
    public StudentEntity getByNumber(String number) {
        LambdaQueryWrapper<StudentEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StudentEntity::getNumber, number);

        return mapper.selectOne(wrapper);
    }

    public int insert(StudentEntity entity) {
        return mapper.insert(entity);
    }

    public int delete(Integer id) {
        return mapper.deleteById(id);
    }

    public int update(StudentEntity entity) {
        return mapper.updateById(entity);
    }

    public int count(String majorName, String className, String name) {
        return mapper.count(majorName, className, name);
    }

    public List<StudentItemVO> getPage(Integer index, String majorName, String className, String name) {
        Page<StudentItemVO> page = new Page<>(index, PAGE_SIZE);

        return mapper.getPage(page, majorName, className, name).getRecords();
    }

    //    根据班级id查询学生实体数量
    public Integer countByClassId(Integer id) {
        LambdaQueryWrapper<StudentEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StudentEntity::getClassId, id);

        return mapper.selectCount(wrapper);
    }

    //    查询所有的学生id和学生姓名，并以列表形式返回
    public List<StudentEntity> listName() {
        LambdaQueryWrapper<StudentEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(StudentEntity::getId, StudentEntity::getName);

        return mapper.selectList(wrapper);
    }

    //    根据学生id查询学院
    public Integer getDepartmentIdById(Integer studentId) {
        return mapper.getDepartmentIdById(studentId);
    }

    //    根据学生id查询成绩
    public Integer getGradeById(Integer studentId) {
        return mapper.getGradeById(studentId);
    }

    //    根据学生id获取学生基本信息
    public StudentInfoVO getStudentInfoById(Integer studentId) {
        return mapper.getStudentInfoById(studentId);
    }


}
