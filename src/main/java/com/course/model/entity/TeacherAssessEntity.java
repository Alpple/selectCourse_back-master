package com.course.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@TableName("teacher_assess")
@Data
public class TeacherAssessEntity {
    //    教师评价实体
    public static final String ID = "assess_id";
    public static final String STUDENT_ID = "assess_student_id";
    public static final String COURSE_ID = "assess_course_id";
    public static final String QUALITY = "assess_course_quality";
    public static final String STYLE = "assess_teaching_style";
    public static final String INTERACTION = "assess_interaction";
    public static final String COMMENT = "assess_comment";
    public static final String CLARITY = "assess_clarity";

    @NotNull
    @TableId(value = ID, type = IdType.AUTO)
    private Integer id;

    @NotNull(message = "必须选择学生")
    @TableField(STUDENT_ID)
    private Integer studentId;

    @NotNull(message = "必须选择课程")
    @TableField(COURSE_ID)
    private Integer courseId;

    @Range(min = 0, max = 100, message = "分数必须在0-100之间")
    @TableField(QUALITY)
    private Integer quality;

    @Range(min = 0, max = 100, message = "分数必须在0-100之间")
    @TableField(STYLE)
    private Integer style;

    @Range(min = 0, max = 100, message = "分数必须在0-100之间")
    @TableField(INTERACTION)
    private Integer interaction;

    @NotBlank(message = "评价内容不能为空")
    @TableField(COMMENT)
    private String comment;

    @Range(min = 0, max = 100, message = "分数必须在0-100之间")
    @TableField(CLARITY)
    private Integer clarity;

}
