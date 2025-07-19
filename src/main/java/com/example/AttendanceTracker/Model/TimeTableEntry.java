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
@Table(name = "timetable")
public class TimeTableEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int timetableID;

    private int teacherID;
    private int courseID;
    private String dayOfWeek;
    private String startTime;
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