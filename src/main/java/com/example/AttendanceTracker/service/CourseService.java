package com.example.AttendanceTracker.service;


import com.example.AttendanceTracker.Model.Course;
import com.example.AttendanceTracker.repositry.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRepo courseRepo;
    public List<Course> getCourse() {
      return courseRepo.findAll();

    }

    public void AddCourse(Course course) {
        // Save the course using your repository
        courseRepo.save(course);
    }
    @Autowired
    private CourseRepo courseRepository;

    public Course getCourseById(int courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

}
