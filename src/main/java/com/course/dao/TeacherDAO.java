package com.course.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.course.dao.mapper.TeacherMapper;
import com.course.model.entity.TeacherEntity;
import com.course.model.vo.response.table.TeacherCourseItemVO;
import com.course.model.vo.response.TeacherInfoVO;
import com.course.model.vo.response.table.TeacherItemVO;
import com.course.model.vo.response.table.TimetableItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherDAO extends BaseDAO {
    public static final int PAGE_SIZE = 20;

    private final TeacherMapper mapper;

    public TeacherDAO(TeacherMapper mapper) {
        this.mapper = mapper;
    }

    public int insert(TeacherEntity entity) {
        return mapper.insert(entity);
    }

    public int delete(Integer id) {
        return mapper.deleteById(id);
    }

    public TeacherEntity get(Integer id) {
        return mapper.selectById(id);
    }

    public int update(TeacherEntity entity) {
        return mapper.updateById(entity);
    }

    public int count(String departmentName, String name) {
        return mapper.count(departmentName, name);
    }

    public List<TeacherItemVO> getPage(Integer index, String departmentName, String name) {
        Page<TeacherItemVO> page = new Page<>(index, PAGE_SIZE);

        return mapper.getPage(page, departmentName, name).getRecords();
    }

    //    根据教师工号查询教师实体
    public TeacherEntity getByNumber(String number) {
        LambdaQueryWrapper<TeacherEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TeacherEntity::getNumber, number);

        return mapper.selectOne(queryWrapper);
    }

    //    根据学院id查询教师实体数量
    public Integer countByDepartmentId(Integer departmentId) {
        LambdaQueryWrapper<TeacherEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TeacherEntity::getDepartmentId, departmentId);

        return mapper.selectCount(queryWrapper);
    }

    //    查询所有教师的id和名称，并以列表形式返回
    public List<TeacherEntity> listName() {
        LambdaQueryWrapper<TeacherEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(TeacherEntity::getId, TeacherEntity::getName);

        return mapper.selectList(queryWrapper);
    }

    public List<TimetableItemVO> listTeacherTimetable(Integer teacherId) {
        return mapper.listTeacherTimetable(teacherId);
    }

    public List<TeacherCourseItemVO> listTeacherCourse(Integer teacherId) {
        return mapper.listTeacherCourse(teacherId);
    }

    public TeacherInfoVO getTeacherInfoById(Integer teacherId) {
        return mapper.getTeacherInfoById(teacherId);
    }
}
