package com.example.AttendanceTracker.repositry;

import com.example.AttendanceTracker.Model.Course;
import com.example.AttendanceTracker.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student,String> {
//    List<Student> findByCourses(Course course);

}
