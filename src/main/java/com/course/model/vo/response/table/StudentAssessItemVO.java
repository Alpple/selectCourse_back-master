package com.course.model.vo.response.table;

import lombok.Data;

@Data
public class StudentAssessItemVO {
    private String courseName;
    private String studentName;
    private Integer quality;
    private Integer style;
    private Integer interaction;
    private Integer clarity;
    private String comment;
}
