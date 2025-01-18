package com.course.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.course.model.entity.AdminEntity;
import org.springframework.stereotype.Repository;

//该类是一个数据访问对象，负责与数据库进行交互
@Repository
public interface AdminMapper extends BaseMapper<AdminEntity> {
//    BaseMapper提供了常用的 CRUD 操作
}
