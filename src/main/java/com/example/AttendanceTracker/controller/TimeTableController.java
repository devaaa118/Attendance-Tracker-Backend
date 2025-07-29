package com.example.AttendanceTracker.controller;

import com.example.AttendanceTracker.Model.TimeTableEntry;
import com.example.AttendanceTracker.repositry.TimeTableRepository;
import com.example.AttendanceTracker.service.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeTableController

{

    @Autowired
    TimeTableService timeTableService;

    @Autowired
    TimeTableRepository timetableRepository;

    @GetMapping("/teacher/{teacherID}/timetable")
    public List<TimeTableEntry> getTimeTable(@PathVariable int teacherID) {
        return timeTableService.getTimeTableForTeacher(teacherID);
    }
    @GetMapping("/teacher/{teacherID}/course/{courseID}/timetable")
    public List<TimeTableEntry> getCoursePeriods(
            @PathVariable int teacherID,
            @PathVariable int courseID) {
        return timetableRepository.findByTeacherIDAndCourseID(teacherID, courseID);
    }
    @PostMapping("/timetable/assign")
    public ResponseEntity<String> assignTimetable(@RequestBody TimeTableEntry entry) {
        boolean success = timeTableService.addEntry(entry);
        if (success) {
            return ResponseEntity.ok("Timetable entry added");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving timetable entry");
        }
    }


}
