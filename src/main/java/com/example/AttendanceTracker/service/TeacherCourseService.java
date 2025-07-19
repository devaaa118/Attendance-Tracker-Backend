package com.example.AttendanceTracker.service;

import com.example.AttendanceTracker.Model.TeacherCourse;
import com.example.AttendanceTracker.Model.Teacher;
import com.example.AttendanceTracker.Model.Course;
import com.example.AttendanceTracker.repositry.TeacherCourseRepo;
import com.example.AttendanceTracker.repositry.TeacherRepo;
import com.example.AttendanceTracker.repositry.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherCourseService {
    @Autowired
    private TeacherCourseRepo teacherCourseRepo;
    
    @Autowired
    private TeacherRepo teacherRepo;
    
    @Autowired
    private CourseRepo courseRepo;


    public List<TeacherCourse> getAllTeacherCourses() {
        return teacherCourseRepo.findAll();
    }

    public TeacherCourse getTeacherCourseById(int teacherCourseID) {
        return teacherCourseRepo.findById(teacherCourseID).orElse(null);
    }

    public boolean deleteTeacherCourse(int teacherCourseID) {
        if (teacherCourseRepo.existsById(teacherCourseID)) {
            teacherCourseRepo.deleteById(teacherCourseID);
            return true;
        }
        return false;
    }



}