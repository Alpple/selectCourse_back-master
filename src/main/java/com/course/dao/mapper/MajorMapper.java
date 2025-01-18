package com.course.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.course.model.entity.MajorEntity;
import com.course.model.vo.response.table.MajorItemVO;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorMapper extends BaseMapper<MajorEntity> {
    //    根据学院名称和专业名称统计符合条件的专业数量，并返回数量
    Integer count(@Param("departmentName") String departmentName, @Param("name") String name);

    //    根据学院名称和专业名称进行分页查询，返回当前页的专业列表
    IPage<MajorItemVO> getPage(IPage<MajorItemVO> page, @Param("department") String departmentName, @Param("name") String name);
}
