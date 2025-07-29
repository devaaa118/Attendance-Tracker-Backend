package com.example.AttendanceTracker.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Student")
@Data
@NoArgsConstructor
public class Student {
    @Id
    @Column(name = "studentID")
    String studentID;

    @Column(name = "studentName")
    String studentName;

    public Student(String studentID, String studentName) {
        this.studentID = studentID;
        this.studentName = studentName;
    }




    }

