package com.example.AttendanceTracker.repositry;

import com.example.AttendanceTracker.Model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance,Integer>
{
    List<Attendance> findByAttendanceDate(LocalDate date);


    List<Attendance> findByCourseIDAndAttendanceDate(int courseID, LocalDate date);

    Optional<Attendance> findByStudentIDAndCourseIDAndAttendanceDate(String studentID, int courseID, LocalDate attendanceDate);
}
