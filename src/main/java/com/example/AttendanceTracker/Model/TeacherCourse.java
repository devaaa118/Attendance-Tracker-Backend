package com.example.AttendanceTracker.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "teacher_course")
public class TeacherCourse {
    @Id
    @Column(name = "teacherCourseID")
    private int teacherCourseID;

    @ManyToOne
    @JoinColumn(name = "teacherID")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "courseID")
    private Course course;

    @Column(name = "teacherName")
    private String teacherName;

    @Column(name = "courseName")
    private String courseName;
    // Constructors
    public TeacherCourse() {}
    public TeacherCourse(int teacherCourseID,Teacher teacher, Course course,String teacherName,String courseName) {
        this.teacherCourseID = teacherCourseID;
        this.teacher = teacher;
        this.course = course;
        this.teacherName = teacherName;
        this.courseName = courseName;
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

    // Getters and setters
    public int getTeacherCourseID() { return teacherCourseID; }
    public void setTeacherCourseID(int teacherCourseID) { this.teacherCourseID = teacherCourseID; }
    public Teacher getTeacher() { return teacher; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }
    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
} 