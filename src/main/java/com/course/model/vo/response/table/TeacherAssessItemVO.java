package com.course.model.vo.response.table;

import lombok.Data;

@Data
public class TeacherAssessItemVO {
    //    评价教师VO
    private Integer assessId;
    private String courseName;
    private String teacherName;
    private Integer quality;
    private Integer style;
    private Integer interaction;
    private Integer clarity;
    private String comment;
    private Integer studentId;
    private Integer courseId;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getClarity() {
        return clarity;
    }

    public void setClarity(Integer clarity) {
        this.clarity = clarity;
    }

    public Integer getInteraction() {
        return interaction;
    }

    public void setInteraction(Integer interaction) {
        this.interaction = interaction;
    }

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getAssessId() {
        return assessId;
    }

    public void setAssessId(Integer assessId) {
        this.assessId = assessId;
    }
}
