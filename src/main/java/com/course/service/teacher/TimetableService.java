package com.course.service.teacher;

import com.course.manager.teacher.TimetableManager;
import com.course.model.vo.response.ResultVO;
import com.course.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class TimetableService extends BaseService {
    private final TimetableManager manager;

    public TimetableService(TimetableManager manager) {
        this.manager = manager;
    }

    public ResultVO get() {
        Integer teacherId = getUserId();
        return result(manager.listTeacherTimetable(teacherId));
    }
}
