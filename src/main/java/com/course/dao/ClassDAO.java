package com.course.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.course.dao.mapper.ClassMapper;
import com.course.model.entity.ClassEntity;
import com.course.model.vo.response.table.MajorItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClassDAO extends BaseDAO {
    public static final int PAGE_SIZE = 20;

    private final ClassMapper mapper;

    public ClassDAO(ClassMapper mapper) {
        this.mapper = mapper;
    }

    //    插入班级
    public int insert(ClassEntity entity) {
        return mapper.insert(entity);
    }

    //    删除班级
    public int delete(Integer id) {
        return mapper.deleteById(id);
    }

    //    查询单个班级
    public ClassEntity get(Integer id) {
        return mapper.selectById(id);
    }

    //    更新班级
    public int update(ClassEntity entity) {
        return mapper.updateById(entity);
    }

    //    统计班级数量
    public int count(String departmentName, String majorName, String name) {
        return mapper.count(departmentName, majorName, name);
    }

    //    获取分页班级
    public List<MajorItemVO> getPage(Integer index, String departmentName, String majorName, String name) {
        Page<MajorItemVO> page = new Page<>(index, PAGE_SIZE);

        return mapper.getPage(page, departmentName, majorName, name).getRecords();
    }

    //    根据专业 ID 统计班级数量
    public Integer countByMajorId(Integer id) {
        LambdaQueryWrapper<ClassEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ClassEntity::getMajorId, id);

        return mapper.selectCount(wrapper);
    }

    //    列出班级名称
    public List<ClassEntity> listName() {
        LambdaQueryWrapper<ClassEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(ClassEntity::getId, ClassEntity::getName);

        return mapper.selectList(wrapper);
    }
}
