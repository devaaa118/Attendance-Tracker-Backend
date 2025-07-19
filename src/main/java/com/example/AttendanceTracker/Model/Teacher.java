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

    // Removed teacherCourses and related methods for TeacherCourse join entity approach
    private String teacherName;
    private String teacherEmail;
    private String teacherPassword;

    public Teacher(int teacherID, String teacherName, String teacherEmail, String teacherPassword) {
        this.teacherID = teacherID;
        this.teacherName = teacherName;
        this.teacherEmail = teacherEmail;
        this.teacherPassword = teacherPassword;
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
        return null;
    }

    public void setCourses(List<Course> teacherCourses) {
        // This method is no longer needed as teacherCourses is removed
    }
}
