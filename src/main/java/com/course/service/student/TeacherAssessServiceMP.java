package com.course.service.student;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.course.dao.mapper.TeacherAssessMapper;
import com.course.model.entity.TeacherAssessEntity;
import org.springframework.stereotype.Service;

@Service
public  class TeacherAssessServiceMP
        extends ServiceImpl<TeacherAssessMapper, TeacherAssessEntity>
        implements IService<TeacherAssessEntity> {

}