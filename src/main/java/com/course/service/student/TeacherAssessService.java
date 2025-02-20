package com.course.service.student;

import com.course.dao.mapper.TeacherAssessMapper;
import com.course.model.entity.TeacherAssessEntity;
import com.course.model.vo.response.ResultVO;
import com.course.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherAssessService extends BaseService {
    @Autowired
    private TeacherAssessMapper teacherAssessMapper;
    @Autowired
    private TeacherAssessServiceMP teacherAssessServiceMP;

    public ResultVO save(TeacherAssessEntity entity) {
        return result(teacherAssessServiceMP.save(entity));
    }

    public ResultVO queryStudentViewAssessList() {
//        Page<TeacherAssessEntity> pageQ = new Page<>(page, pageSize);
//        return result(
//                teacherAssessServiceMP.page(
//                        pageQ,
//                        Wrappers.<TeacherAssessEntity>lambdaQuery()
//                                .select()
//                                .eq(TeacherAssessEntity::getCourseId, courseId)
//                )
//        );
        return result(teacherAssessMapper.selectAssessJoinTeacherAndCourseList(getUserId()));
    }
    public ResultVO queryTeacherViewAssessList(int courseId) {
        return result(teacherAssessMapper.selectAssessJoinStudentAndCourseList(courseId));
    }
}
