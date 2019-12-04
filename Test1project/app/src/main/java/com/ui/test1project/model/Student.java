package com.ui.test1project.model;

import java.util.List;

public class Student {
    private String studentName;
    private List<String> coursesSelected;
    private int totalHours;
    private int totalFees;
    private boolean isGraduated;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<String> getCoursesSelected() {
        return coursesSelected;
    }

    public void setCoursesSelected(List<String> coursesSelected) {
        this.coursesSelected = coursesSelected;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

    public boolean isGraduated() {
        return isGraduated;
    }

    public void setGraduated(boolean graduated) {
        isGraduated = graduated;
    }

    public int getTotalFees() {
        return totalFees;
    }

    public void setTotalFees(int totalFees) {
        this.totalFees = totalFees;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentName='" + studentName + '\'' +
                ", coursesSelected=" + coursesSelected +
                ", totalHours=" + totalHours +
                ", isGraduated=" + isGraduated +
                '}';
    }
}
