package com.course.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.course.model.entity.StudentCourseEntity;
import com.course.model.vo.response.table.StudentCourseItemVO;
import com.course.model.vo.response.table.StudentCourseSelectedItemVO;
import com.course.model.vo.response.table.TeacherGradeItemVO;
import com.course.model.vo.response.table.TimetableItemVO;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseMapper extends BaseMapper<StudentCourseEntity> {
    //    根据班级名称、课程名称和学生名称统计符合条件的选课记录数量
    Integer count(@Param("className") String className, @Param("courseName") String courseName, @Param("studentName") String studentName);

    //    获取分页选课记录
    IPage<StudentCourseItemVO> getPage(IPage<StudentCourseItemVO> page, @Param("className") String className, @Param("courseName") String courseName, @Param("studentName") String studentName);

    //    统计教师评分数量
    Integer countTeacherGrade(@Param("teacherId") Integer teacherId, @Param("courseName") String courseName, @Param("studentName") String studentName);

    //    获取教师评分的分页方法
    IPage<TeacherGradeItemVO> getTeacherGradePage(IPage<TeacherGradeItemVO> page, @Param("teacherId") Integer teacherId, @Param("courseName") String courseName, @Param("studentName") String studentName);

    //    列出学生已选课程
    List<StudentCourseSelectedItemVO> listStudentCourseSelected(Integer studentId);

    //    统计学生在特定时间段内的选课数量
    Integer countStudentCourseSelectedByTimePart(@Param("studentId") Integer studentId, @Param("timePart") String timePart);

    //    列出学生课程表的方法
    List<TimetableItemVO> listStudentTimetable(Integer studentId);
}
