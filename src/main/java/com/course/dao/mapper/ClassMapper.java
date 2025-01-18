package com.course.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.course.model.entity.ClassEntity;
import com.course.model.vo.response.table.MajorItemVO;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassMapper extends BaseMapper<ClassEntity> {
    //    根据给定的学院名称、专业名称和班级名称统计符合条件的记录数量
    Integer count(@Param("departmentName") String departmentName, @Param("majorName") String majorName, @Param("name") String name);

    //    根据给定的学院名称、专业名称和班级名称获取分页结果
    IPage<MajorItemVO> getPage(IPage<MajorItemVO> page, @Param("departmentName") String departmentName, @Param("majorName") String majorName, @Param("name") String name);
}
