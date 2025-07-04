package com.course.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.course.dao.mapper.MajorMapper;
import com.course.model.entity.MajorEntity;
import com.course.model.vo.response.table.MajorItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MajorDAO extends BaseDAO {
    public static final int PAGE_SIZE = 20;

    private final MajorMapper mapper;

    public MajorDAO(MajorMapper mapper) {
        this.mapper = mapper;
    }

    public int insert(MajorEntity entity) {
        return mapper.insert(entity);
    }

    public int delete(Integer id) {
        return mapper.deleteById(id);
    }

    public MajorEntity get(Integer id) {
        return mapper.selectById(id);
    }

    public int update(MajorEntity entity) {
        return mapper.updateById(entity);
    }

    public int count(String departmentName, String name) {
        return mapper.count(departmentName, name);
    }

    public List<MajorItemVO> getPage(Integer index, String departmentName, String name) {
        Page<MajorItemVO> page = new Page<>(index, PAGE_SIZE);

        return mapper.getPage(page, departmentName, name).getRecords();
    }

    //    根据学院id统计属于该学院的专业数量
    public Integer countByDepartmentId(Integer departmentId) {
        LambdaQueryWrapper<MajorEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MajorEntity::getDepartmentId, departmentId);

        return mapper.selectCount(wrapper);
    }

    //    查询所有专业的id和名称，并返回这些数据的列表
    public List<MajorEntity> listName() {
        LambdaQueryWrapper<MajorEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(MajorEntity::getId, MajorEntity::getName);

        return mapper.selectList(wrapper);
    }
}
