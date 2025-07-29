package com.example.AttendanceTracker.controller;


import com.example.AttendanceTracker.Model.Student;
import com.example.AttendanceTracker.service.StudentService;
import com.example.AttendanceTracker.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController


@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;

    @GetMapping("/Students/GetStudents")
    public List<Student> GetStudents(){
      return  studentService.GetStudents();
    }
    @PostMapping("Students/AddStudents")
    public void AddStudent(@RequestBody Student student, @RequestParam String adminEmail){
        var admin = teacherService.getTeacherByEmail(adminEmail);
        if (admin != null && "admin".equals(admin.getRole())) {
            studentService.AddStudent(student);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only admin can add students");
        }
    }

//    @GetMapping("/Students/GetStudentsByCourse")
//    public List<Student> getStudentsByCourse(@RequestParam int courseId) {
//        return studentService.getStudentByCourse(courseId);
//    }

}


