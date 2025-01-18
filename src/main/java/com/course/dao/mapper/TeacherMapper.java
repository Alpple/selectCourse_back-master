package com.course.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.course.model.entity.TeacherEntity;
import com.course.model.vo.response.table.TeacherCourseItemVO;
import com.course.model.vo.response.TeacherInfoVO;
import com.course.model.vo.response.table.TeacherItemVO;
import com.course.model.vo.response.table.TimetableItemVO;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper extends BaseMapper<TeacherEntity> {
//    查询符合学院名称和教师名称的计数
    Integer count(@Param("departmentName")String departmentName, @Param("name")String name);
//    分页查询符合学院名称和教师名称列表
    IPage<TeacherItemVO> getPage(IPage<TeacherItemVO> page, @Param("departmentName")String departmentName, @Param("name")String name);
//    通过教师id查询与该教师相关的课程安排
    List<TimetableItemVO> listTeacherTimetable(Integer teacherId);
//    通过教师id查询与该教师相关的课程详情
    List<TeacherCourseItemVO> listTeacherCourse(Integer teacherId);
//    根据教师id查询与该教师相关的基本信息
    TeacherInfoVO getTeacherInfoById(Integer teacherId);
}
