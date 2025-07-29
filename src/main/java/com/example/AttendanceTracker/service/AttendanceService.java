package com.example.AttendanceTracker.service;

import com.example.AttendanceTracker.Model.Attendance;
import com.example.AttendanceTracker.repositry.AttendanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {
    @Autowired
    AttendanceRepo attendanceRepo;
    public void PostAttendance(Attendance attendance) {
        attendanceRepo.save(attendance);
    }

    public List<Attendance> getAttendance() {
       return attendanceRepo.findAll();
    }
    public List<Attendance> getAttendanceByCourseAndDate(int courseID, LocalDate date) {
        return attendanceRepo.findByCourseIDAndAttendanceDate(courseID, date);
    }

    public boolean updateAttendanceList(List<Attendance> updatedList) {
        try {
            for (Attendance newRecord : updatedList) {
                Optional<Attendance> existing = attendanceRepo.findByStudentIDAndCourseIDAndAttendanceDate(
                        newRecord.getStudentID(), newRecord.getCourseID(), newRecord.getAttendanceDate()
                );

                if (existing.isPresent()) {
                    Attendance old = existing.get();
                    old.setAttendanceStatus(newRecord.getAttendanceStatus());
                    attendanceRepo.save(old);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public List<Attendance> RetrieveFromDate(LocalDate date) {
        return attendanceRepo.findByAttendanceDate(date);
    }
}
