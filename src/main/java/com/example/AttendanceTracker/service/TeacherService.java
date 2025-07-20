package com.example.AttendanceTracker.service;

import com.example.AttendanceTracker.Model.Teacher;
import com.example.AttendanceTracker.Model.TeacherCourse;
import com.example.AttendanceTracker.Model.Course;
import com.example.AttendanceTracker.repositry.TeacherRepo;
import com.example.AttendanceTracker.repositry.TeacherCourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private TeacherCourseRepo teacherCourseRepo;

    // Simple password hashing method
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return password; // fallback to plain text if hashing fails
        }
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepo.findAll();
    }

    public Teacher getTeacherById(int teacherID) {
        Optional<Teacher> teacher = teacherRepo.findById(teacherID);
        return teacher.orElse(null);
    }

    public Teacher addTeacher(Teacher teacher) {
        // Store password as plain text (as per your SQL insertion method)
        return teacherRepo.save(teacher);
    }

    public Teacher updateTeacher(int teacherID, Teacher teacherDetails) {
        Optional<Teacher> optionalTeacher = teacherRepo.findById(teacherID);
        if (optionalTeacher.isPresent()) {
            Teacher teacher = optionalTeacher.get();
            teacher.setTeacherName(teacherDetails.getTeacherName());
            teacher.setTeacherEmail(teacherDetails.getTeacherEmail());
            // Store password as plain text (as per your SQL insertion method)
            teacher.setTeacherPassword(teacherDetails.getTeacherPassword());
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
        Teacher teacher = teacherRepo.findByTeacherEmail(email.trim());
        if (teacher != null) {
            String dbPassword = teacher.getTeacherPassword() != null ? teacher.getTeacherPassword().trim() : "";
            String inputPassword = password != null ? password.trim() : "";
            System.out.println("DB password: [" + dbPassword + "], Input password: [" + inputPassword + "]");
            if (dbPassword.equals(inputPassword)) {
                return teacher;
            } else {
                System.out.println("Password mismatch!");
            }
        } else {
            System.out.println("Teacher not found for email: " + email);
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