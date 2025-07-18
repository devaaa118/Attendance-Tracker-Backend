package com.example.AttendanceTracker.controller;

import com.example.AttendanceTracker.Model.Course;
import com.example.AttendanceTracker.Model.Teacher;
import com.example.AttendanceTracker.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@CrossOrigin(origins = "*")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/all")
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{teacherID}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable int teacherID) {
        Teacher teacher = teacherService.getTeacherById(teacherID);
        if (teacher != null) {
            return ResponseEntity.ok(teacher);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{teacherID}/teacherCourses")
    public ResponseEntity<List<Course>> getTeacherCourses(@PathVariable int teacherID) {
        Teacher teacher = teacherService.getTeacherById(teacherID);
        if (teacher != null) {
            return ResponseEntity.ok(teacher.getTeacherCourses());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public Teacher addTeacher(@RequestBody Teacher teacher) {
        return teacherService.addTeacher(teacher);
    }

    @PutMapping("/update/{teacherID}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable int teacherID, @RequestBody Teacher teacher) {
        Teacher updated = teacherService.updateTeacher(teacherID, teacher);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{teacherID}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable int teacherID) {
        boolean deleted = teacherService.deleteTeacher(teacherID);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
} 