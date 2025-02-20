package com.course.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.course.model.entity.TeacherAssessEntity;
import com.course.model.vo.response.table.StudentAssessItemVO;
import com.course.model.vo.response.table.TeacherAssessItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherAssessMapper extends BaseMapper<TeacherAssessEntity> {
    List<TeacherAssessItemVO> selectAssessJoinTeacherAndCourseList(int studentId);
    List<StudentAssessItemVO> selectAssessJoinStudentAndCourseList(int courseId);
}
