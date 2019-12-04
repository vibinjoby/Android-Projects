package com.ui.test1project.model;

public class CoursePojo {
    private int courseId;
    private String courseName;
    private int courseFees;
    private int courseHours;

    public CoursePojo(int courseId, String courseName, int courseFees, int courseHours) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseFees = courseFees;
        this.courseHours = courseHours;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCourseFees() {
        return courseFees;
    }

    public int getCourseHours() {
        return courseHours;
    }

    @Override
    public String toString() {
        return "CoursePojo{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseFees=" + courseFees +
                ", courseHours=" + courseHours +
                '}';
    }
}
