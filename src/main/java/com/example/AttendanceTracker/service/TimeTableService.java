package com.example.AttendanceTracker.service;

import com.example.AttendanceTracker.Model.TimeTableEntry;
import com.example.AttendanceTracker.repositry.TimeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeTableService {
    @Autowired
    private TimeTableRepository timeTableRepository;

    public List<TimeTableEntry> getTimeTableForTeacher(int teacherID) {
        return timeTableRepository.findByTeacherID(teacherID);
    }

    public boolean addEntry(TimeTableEntry entry) {
        return timeTableRepository.save(entry) != null;
    }

}
