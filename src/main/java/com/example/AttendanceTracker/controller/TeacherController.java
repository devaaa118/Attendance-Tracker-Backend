package com.example.AttendanceTracker.controller;

import com.example.AttendanceTracker.Model.Course;
import com.example.AttendanceTracker.Model.Teacher;
import com.example.AttendanceTracker.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher")
@CrossOrigin(origins = "*")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/getAllTeachers")
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
    @PostMapping("/{id}/assignCourses")
    public ResponseEntity<String> assignCoursesToTeacher(@PathVariable("id") int teacherID,
                                                         @RequestBody List<Integer> courseIDs) {
        try {
            boolean success = teacherService.assignCoursesToTeacher(teacherID, courseIDs);
            if (success) {
                return ResponseEntity.ok("Courses assigned successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid teacher or courses");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while assigning courses: " + e.getMessage());
        }
    }


    @GetMapping("/{teacherID}/teacherCourses")
    public ResponseEntity<List<Course>> getTeacherCourses(@PathVariable int teacherID) {
        List<Course> courses = teacherService.getCoursesForTeacher(teacherID);
        if (!courses.isEmpty()) {
            return ResponseEntity.ok(courses);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public Teacher addTeacher(@RequestBody Teacher teacher, @RequestParam String adminEmail) {
        Teacher admin = teacherService.getTeacherByEmail(adminEmail);
        if (admin != null && "admin".equals(admin.getRole())) {
            return teacherService.addTeacher(teacher);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only admin can add teachers");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Teacher> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");
        Teacher teacher = teacherService.login(email, password);
        if (teacher != null) {
            return ResponseEntity.ok(teacher);
        } else {
            return ResponseEntity.status(401).build();
        }
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