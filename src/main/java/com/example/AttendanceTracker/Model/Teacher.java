package com.example.AttendanceTracker.Model;

import jakarta.persistence.*;

import java.util.List;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;


@Entity

public class Teacher {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int teacherID;

    public List<Course> getTeacherCourses() {
        return teacherCourses;
    }

    public void setTeacherCourses(List<Course> teacherCourses) {
        this.teacherCourses = teacherCourses;
    }

    private String teacherName;
    private String teacherEmail;
    private String teacherPassword;

    @ManyToMany
    @JoinTable(
        name = "teacher_course",
        joinColumns = @JoinColumn(name = "teacherID"),
        inverseJoinColumns = @JoinColumn(name = "courseID")
    )
    private List<Course> teacherCourses;

    public Teacher(int teacherID, String teacherName, String teacherEmail, String teacherPassword, List<Course> teacherCourses) {
        this.teacherID = teacherID;
        this.teacherName = teacherName;
        this.teacherEmail = teacherEmail;
        this.teacherPassword = teacherPassword;
        this.teacherCourses = teacherCourses;
    }

    // Constructors
    public Teacher() {}

  

    // Getters and Setters

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getTeacherPassword() {
        return teacherPassword;
    }

    public void setTeacherPassword(String teacherPassword) {
        this.teacherPassword = teacherPassword;
    }

    public List<Course> getCourses() {
        return teacherCourses   ;
    }

    public void setCourses(List<Course> teacherCourses) {
        this.teacherCourses = teacherCourses;
    }
}
