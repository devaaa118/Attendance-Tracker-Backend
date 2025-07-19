package com.example.AttendanceTracker.controller;

import com.example.AttendanceTracker.Model.TeacherCourse;
import com.example.AttendanceTracker.Model.TimeTableEntry;
import com.example.AttendanceTracker.service.TeacherCourseService;
import com.example.AttendanceTracker.service.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teachercourse")
@CrossOrigin(origins = "*")
public class TeacherCourseController {

    @Autowired
    private TeacherCourseService teacherCourseService;



    @GetMapping("/all")
    public List<TeacherCourse> getAllTeacherCourses() {
        return teacherCourseService.getAllTeacherCourses();
    }

    @GetMapping("/{teacherCourseID}")
    public ResponseEntity<TeacherCourse> getTeacherCourseById(@PathVariable int teacherCourseID) {
        TeacherCourse teacherCourse = teacherCourseService.getTeacherCourseById(teacherCourseID);
        if (teacherCourse != null) {
            return ResponseEntity.ok(teacherCourse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{teacherCourseID}")
    public ResponseEntity<Void> deleteTeacherCourse(@PathVariable int teacherCourseID) {
        boolean deleted = teacherCourseService.deleteTeacherCourse(teacherCourseID);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


} 