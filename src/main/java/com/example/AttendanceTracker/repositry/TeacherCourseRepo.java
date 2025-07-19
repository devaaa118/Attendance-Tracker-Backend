package com.example.AttendanceTracker.repositry;

import com.example.AttendanceTracker.Model.TeacherCourse;
import com.example.AttendanceTracker.Model.Teacher;
import com.example.AttendanceTracker.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherCourseRepo extends JpaRepository<TeacherCourse, Integer> {
    List<TeacherCourse> findByTeacher(Teacher teacher);
    List<TeacherCourse> findByCourse(Course course);

   }