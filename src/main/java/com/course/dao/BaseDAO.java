package com.course.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public class BaseDAO {
    //    通用的分页查询方法
    <T> List<T> selectPage(BaseMapper<T> mapper, LambdaQueryWrapper<T> wrapper, int index, int size) {
        Page<T> page = new Page<>(index, size);
        return mapper.selectPage(page, wrapper).getRecords();
    }
}
