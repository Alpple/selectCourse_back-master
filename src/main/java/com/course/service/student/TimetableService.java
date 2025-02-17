package com.course.service.student;


import com.course.manager.student.TimetableManager;
import com.course.model.vo.response.ResultVO;
import com.course.service.BaseService;
import org.springframework.stereotype.Service;

@Service("student_timetableService")
public class TimetableService extends BaseService {
    private final TimetableManager manager;

    public TimetableService(TimetableManager manager) {
        this.manager = manager;
    }

    //    获取学生课程表
    public ResultVO get() {
        Integer studentId = getUserId();
        return result(manager.listStudentTimetable(studentId));
    }
}
