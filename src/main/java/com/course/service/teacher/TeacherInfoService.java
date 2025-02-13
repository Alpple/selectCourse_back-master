package com.course.service.teacher;

import com.course.manager.teacher.TeacherInfoManager;
import com.course.model.entity.TeacherEntity;
import com.course.model.vo.request.TeacherInfoFormVO;
import com.course.model.vo.response.ResultVO;
import com.course.service.BaseService;
import com.course.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TeacherInfoService extends BaseService {
    private final UserService userService;
    private final TeacherInfoManager manager;

    public TeacherInfoService(UserService userService, TeacherInfoManager manager) {
        this.userService = userService;
        this.manager = manager;
    }

    public ResultVO get() {
        return result(manager.getTeacherInfoById(getUserId()));
    }

    public ResultVO update(@RequestBody @Validated TeacherInfoFormVO teacherInfoForm) {
        TeacherEntity teacher = manager.getTeacherById(getUserId());

        BeanUtils.copyProperties(teacherInfoForm,teacher);
        manager.updateTeacher(teacher);

        return result("更新成功");
    }
}
