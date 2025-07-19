package com.example.AttendanceTracker.repositry;

import com.example.AttendanceTracker.Model.TimeTableEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TimeTableRepository extends JpaRepository<TimeTableEntry, Integer> {
    List<TimeTableEntry> findByTeacherID(int teacherID);

    List<TimeTableEntry> findByTeacherIDAndCourseID(int teacherID, int courseID);
}