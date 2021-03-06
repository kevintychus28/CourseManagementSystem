package com.example.cms.entity;

public class Course {
    private String Cou_id;
    private String Cou_name;
    private String Cou_teacher;
    private String Cou_classroom;
    private String Cou_weekday;
    private String Cou_period;
    private String Cou_exam_time;
    private String start_time;
    private String grade;


    public Course() {
    }

    public String getCou_id() {
        return Cou_id;
    }

    public void setCou_id(String cou_id) {
        Cou_id = cou_id;
    }

    public String getCou_name() {
        return Cou_name;
    }

    public void setCou_name(String cou_name) {
        Cou_name = cou_name;
    }

    public String getCou_teacher() {
        return Cou_teacher;
    }

    public void setCou_teacher(String cou_teacher) {
        Cou_teacher = cou_teacher;
    }

    public String getCou_classroom() {
        return Cou_classroom;
    }

    public void setCou_classroom(String cou_classroom) {
        Cou_classroom = cou_classroom;
    }

    public String getCou_weekday() {
        return Cou_weekday;
    }

    public void setCou_weekday(String cou_weekday) {
        Cou_weekday = cou_weekday;
    }

    public String getCou_period() {
        return Cou_period;
    }

    public void setCou_period(String cou_period) {
        Cou_period = cou_period;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getCou_exam_time() {
        return Cou_exam_time;
    }

    public void setCou_exam_time(String cou_exam_time) {
        Cou_exam_time = cou_exam_time;
    }

    @Override
    public String toString() {
        return "Course{" +
                "Cou_name='" + Cou_name + '\'' +
                ", Cou_teacher='" + Cou_teacher + '\'' +
                ", Cou_classroom='" + Cou_classroom + '\'' +
                ", Cou_weekday='" + Cou_weekday + '\'' +
                ", Cou_period='" + Cou_period + '\'' +
                ", Cou_exam_time='" + Cou_exam_time + '\'' +
                ", start_time='" + start_time + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
