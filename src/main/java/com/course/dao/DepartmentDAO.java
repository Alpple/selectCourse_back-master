package com.course.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.course.dao.mapper.DepartmentMapper;
import com.course.model.entity.DepartmentEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDAO extends BaseDAO {
    public static final int PAGE_SIZE = 20;

    private final DepartmentMapper mapper;

    public DepartmentDAO(DepartmentMapper mapper) {
        this.mapper = mapper;
    }

    public int insert(DepartmentEntity entity) {
        return mapper.insert(entity);
    }

    public int delete(Integer id) {
        return mapper.deleteById(id);
    }

    public DepartmentEntity get(Integer id) {
        return mapper.selectById(id);
    }

    public int update(DepartmentEntity entity) {
        return mapper.updateById(entity);
    }

    //    根据专业名称统计专业数量
    public int count(String name) {
        LambdaQueryWrapper<DepartmentEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name != null, DepartmentEntity::getName, name);

        return mapper.selectCount(wrapper);
    }

    //    分页查询
    public List<DepartmentEntity> getPage(Integer index, String name) {
        LambdaQueryWrapper<DepartmentEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name != null, DepartmentEntity::getName, name);

        return selectPage(mapper, wrapper, index, PAGE_SIZE);
    }

    //    查询所有学院的id和名称，并以列表形式返回
    public List<DepartmentEntity> listName() {
        LambdaQueryWrapper<DepartmentEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(DepartmentEntity::getId, DepartmentEntity::getName);

        return mapper.selectList(wrapper);
    }
}
