package com.course.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@TableName("class")
@Data
public class ClassEntity {

    public static final String ID = "class_id";
    public static final String MAJOR_ID = "class_major_id";
    public static final String GRADE = "class_grade";
    public static final String NAME = "class_name";

    @NotNull
    @TableId(value = ID,type = IdType.AUTO)
    private Integer id;

    @NotNull(message = "必须选择所属专业")
    @TableField(MAJOR_ID)
    private Integer majorId;

    @NotNull
    @Range(min = 1980,max = 2999,message = "年级范围必须在1980-2999之间")
    @TableField(GRADE)
    private Integer grade;

    @NotBlank(message = "班级名不能为空")
    @TableField(NAME)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

