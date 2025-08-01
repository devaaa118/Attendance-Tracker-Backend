package com.example.AttendanceTracker.controller;

import com.example.AttendanceTracker.Model.Attendance;
import com.example.AttendanceTracker.Model.Student;
import com.example.AttendanceTracker.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

    @GetMapping("Attendance/GetAttendance")
    public List<Attendance> GetAttendance(){
      return  attendanceService.getAttendance();
    }

    @PostMapping("Attendance/PostAttendance")
    public void PostAttendance(@RequestBody Attendance attendance){
        attendanceService.PostAttendance(attendance);
    }
    @GetMapping("Attendance/RetrieveFromDate/{date}")
    public List<Attendance> RetrieveFromDate(@PathVariable("date")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
     return   attendanceService.RetrieveFromDate(date);
    }
    @GetMapping("/GetByCourseAndDate")
    public List<Attendance> getAttendanceByCourseAndDate(@RequestParam int courseID, @RequestParam LocalDate date) {
        return attendanceService.getAttendanceByCourseAndDate(courseID, date);
    }

    @PutMapping("/UpdateAttendanceList")
    public ResponseEntity<Void> updateAttendanceList(@RequestBody List<Attendance> attendanceList) {
        boolean success = attendanceService.updateAttendanceList(attendanceList);
        return success ? ResponseEntity.ok().build() : ResponseEntity.status(500).build();
    }


    @PostMapping("/PostAttendanceBulk")
    public ResponseEntity<?> postAttendanceBulk(@RequestBody List<Attendance> attendanceInstances) {
        for (Attendance instance : attendanceInstances) {
            attendanceService.PostAttendance(instance);
        }
        return ResponseEntity.ok().build();
    }
}
