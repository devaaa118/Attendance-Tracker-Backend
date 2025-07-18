package com.example.AttendanceTracker.repositry;

import com.example.AttendanceTracker.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Integer> {
    Teacher findByTeacherEmailAndTeacherPassword(String teacherEmail, String teacherPassword);
    Teacher findByTeacherEmail(String teacherEmail);
} 