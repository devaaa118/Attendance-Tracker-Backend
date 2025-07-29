package com.example.AttendanceTracker.controller;

import com.example.AttendanceTracker.Model.Course;
import com.example.AttendanceTracker.service.CourseService;
import com.example.AttendanceTracker.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    TeacherService teacherService;

    @GetMapping("Course/GetCourse")
    public List<Course> GetAttendance(){
       return courseService.getCourse();
    }

    @PostMapping("Course/AddCourse")
    public void AddCourse(@RequestBody Course course, @RequestParam String adminEmail) {
        var admin = teacherService.getTeacherByEmail(adminEmail);
        if (admin != null && "admin".equals(admin.getRole())) {
            courseService.AddCourse(course);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only admin can add courses");
        }
    }
}
