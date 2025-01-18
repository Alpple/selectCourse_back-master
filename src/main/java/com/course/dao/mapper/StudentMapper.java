package com.course.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.course.model.entity.StudentEntity;
import com.course.model.vo.response.StudentInfoVO;
import com.course.model.vo.response.table.StudentItemVO;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper extends BaseMapper<StudentEntity> {
//    查询符合专业名称、班级名称、学生姓名的计数
    Integer count(@Param("majorName") String majorName,@Param("className") String className,@Param("name") String name);
//    分页查询符合专业名称、班级名称、学生姓名的列表
    IPage<StudentItemVO> getPage(IPage<StudentItemVO>  page,@Param("majorName") String majorName,@Param("className") String className,@Param("name") String name);
//    根据指定的学生id查询并返回学生所属的学院id
    Integer getDepartmentIdById(Integer studentId);
//    根据指定的学生id查询并返回学生所属的班级id
    Integer getGradeById(Integer studentId);
//    根据指定的学生id查询并返回学生对应的VO
    StudentInfoVO getStudentInfoById(Integer studentId);
}
