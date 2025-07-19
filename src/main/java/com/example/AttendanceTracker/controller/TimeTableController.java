package com.example.AttendanceTracker.controller;

import com.example.AttendanceTracker.Model.TimeTableEntry;
import com.example.AttendanceTracker.repositry.TimeTableRepository;
import com.example.AttendanceTracker.service.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
