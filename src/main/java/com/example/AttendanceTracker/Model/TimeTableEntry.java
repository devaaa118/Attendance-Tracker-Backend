package com.example.AttendanceTracker.Model;

import com.example.AttendanceTracker.Model.Course;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Timetable")
public class TimeTableEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timetableID")
    private int timetableID;

    @Column(name = "teacherID")
    private int teacherID;

    @Column(name = "courseID")
    private int courseID;

    @Column(name = "dayOfWeek")
    private String dayOfWeek;

    @Column(name = "startTime")
    private String startTime;

    @Column(name = "endTime")
    private String endTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "courseID", insertable = false, updatable = false)
    private Course course;

    // getters and setters

    // Optionally, add a getter for courseName for convenience
    public String getCourseName() {
        return course != null ? course.getCourseName() : null;
    }
}