package com.course.service.admin;

import com.course.manager.admin.CourseManager;
import com.course.model.bo.CourseItemBO;
import com.course.model.entity.CourseEntity;
import com.course.model.vo.response.ResultVO;
import com.course.model.vo.response.table.CourseItemVO;
import com.course.service.BaseService;
import com.course.util.LessonTimeConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService extends BaseService {
    private final CourseManager manager;
    private final LessonTimeConverter lessonTimeConverter;

    public CourseService(CourseManager manager, LessonTimeConverter lessonTimeConverter) {
        this.manager = manager;
        this.lessonTimeConverter = lessonTimeConverter;
    }

    //    获取分页数量
    public ResultVO getPageCount(String departmentName, String teacherName, String name) {
        return result(manager.getPageCount(departmentName, teacherName, name));
    }

    //    获取分页课程数据
    public ResultVO getPage(Integer index, String departmentName, String teacherName, String name) {
        List<CourseItemBO> boList = manager.getPage(index, departmentName, teacherName, name);
        List<CourseItemVO> voList = new ArrayList<>();

        for (CourseItemBO bo : boList) {
            CourseItemVO vo = new CourseItemVO();
            BeanUtils.copyProperties(bo, vo);
            vo.setTime(lessonTimeConverter.covertTimePart(bo.getTime()));
            voList.add(vo);
        }

        return result(voList);
    }

    public ResultVO get(Integer id) {
        CourseEntity entity = manager.get(id);
        if (entity == null) {
            return failedResult("课程id：" + id + "不存在！");
        }

        return result(entity);
    }

    public ResultVO delete(Integer id) {
        if (manager.get(id) == null) {
            return failedResult("课程id：" + id + "不存在！");
        }
        if (manager.hasStudentCourse(id)) {
            return failedResult("还有学生未退选此课程");
        }

        manager.delete(id);
        return result("删除成功");
    }

    public ResultVO listName() {
        return result(manager.listName());
    }
}
