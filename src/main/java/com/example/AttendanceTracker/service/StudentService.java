package com.example.AttendanceTracker.service;

import com.example.AttendanceTracker.Model.Student;
import com.example.AttendanceTracker.Model.Course;
import com.example.AttendanceTracker.repositry.StudentRepo;
import com.example.AttendanceTracker.repositry.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepo studentRepo;

    @Autowired
    CourseRepo courseRepo;

    public List<Student> GetStudents() {
        return studentRepo.findAll();
    }

    public void AddStudent(Student student) {
        studentRepo.save(student);
    }

    public Student getStudentById(String studentId) {
        return studentRepo.findById(studentId).orElse(null);
    }

    public Student updateStudent(String studentId, Student studentDetails) {
        Optional<Student> optionalStudent = studentRepo.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setStudentName(studentDetails.getStudentName());
            return studentRepo.save(student);
        }
        return null;
    }

    public boolean deleteStudent(String studentId) {
        if (studentRepo.existsById(studentId)) {
            studentRepo.deleteById(studentId);
            return true;
        }
        return false;
    }


//    public List<Student> getStudentsByCourse(int courseId) {
//        Optional<Course> courseOpt = courseRepo.findById(courseId);
//        if (courseOpt.isPresent()) {
//            Course course = courseOpt.get();
//            return course.getStudents(); // You need to have mappedBy in Course class
//        }
//        return new ArrayList<>();
//    }

//    public boolean assignCoursesToStudent(String studentId, List<Integer> courseIds) {
//        Student student = getStudentById(studentId);
//        if (student == null) return false;
//
//        List<Course> courses = courseRepo.findAllById(courseIds);
//        student.setCourses(courses);
//        studentRepo.save(student);
//        return true;
//    }
}
