package com.example.AttendanceTracker.service;

import com.example.AttendanceTracker.Model.Teacher;
import com.example.AttendanceTracker.Model.TeacherCourse;
import com.example.AttendanceTracker.Model.Course;
import com.example.AttendanceTracker.repositry.TeacherRepo;
import com.example.AttendanceTracker.repositry.TeacherCourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private TeacherCourseRepo teacherCourseRepo;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<Teacher> getAllTeachers() {
        return teacherRepo.findAll();
    }

    public Teacher getTeacherById(int teacherID) {
        Optional<Teacher> teacher = teacherRepo.findById(teacherID);
        return teacher.orElse(null);
    }

    public Teacher addTeacher(Teacher teacher) {
        // Hash the password before saving
        teacher.setTeacherPassword(passwordEncoder.encode(teacher.getTeacherPassword()));
        return teacherRepo.save(teacher);
    }

    public Teacher updateTeacher(int teacherID, Teacher teacherDetails) {
        Optional<Teacher> optionalTeacher = teacherRepo.findById(teacherID);
        if (optionalTeacher.isPresent()) {
            Teacher teacher = optionalTeacher.get();
            teacher.setTeacherName(teacherDetails.getTeacherName());
            teacher.setTeacherEmail(teacherDetails.getTeacherEmail());
            // Hash the password before saving
            teacher.setTeacherPassword(passwordEncoder.encode(teacherDetails.getTeacherPassword()));
            teacher.setCourses(teacherDetails.getCourses());
            return teacherRepo.save(teacher);
        } else {
            return null;
        }
    }

    public boolean deleteTeacher(int teacherID) {
        if (teacherRepo.existsById(teacherID)) {
            teacherRepo.deleteById(teacherID);
            return true;
        } else {
            return false;
        }
    }

    public Teacher login(String email, String password) {
        Teacher teacher = teacherRepo.findByTeacherEmail(email);
        if (teacher != null && passwordEncoder.matches(password, teacher.getTeacherPassword())) {
            return teacher;
        }
        return null;
    }

    public List<Course> getCoursesForTeacher(int teacherID) {
        Teacher teacher = teacherRepo.findById(teacherID).orElse(null);
        if (teacher == null) return List.of();
        List<TeacherCourse> teacherCourses = teacherCourseRepo.findByTeacher(teacher);
        return teacherCourses.stream().map(TeacherCourse::getCourse).toList();
    }
} 