/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation.model;

import itucs.blg361e.courseautomation.utility.SelectOption;
import java.sql.Time;

/**
 *
 * @author Oguzzo
 */
public class OpenCourse extends Course{    

    
    private Integer CRN;
    private Integer courseID;
    private Integer quota;
    private Integer currentStudentCount;
    private Integer teacherID;
    private Integer classID;
    private Time beginTime;
    private Time endTime;
    
    private String beginTimeInString;
    private String endTimeInString;
    
    private String day;
    private SelectOption course;
    private SelectOption class_room;
    
    private SelectOption daySelect;

    

    
    
    public OpenCourse(){
        
    };
    
    public OpenCourse(Integer CRN, Integer courseID, Integer quota, Integer currentStudentCount, Integer teacherID, Integer classID, Time beginTime, Time endTime, String day) {
        this.CRN = CRN;
        this.courseID = courseID;
        this.quota = quota;
        this.currentStudentCount = currentStudentCount;
        this.teacherID = teacherID;
        this.classID = classID;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.day = day;
    }
    
    public Integer getCRN() {
        return CRN;
    }

    public void setCRN(Integer CRN) {
        this.CRN = CRN;
    }

    public SelectOption getCourse() {
        return course;
    }

    public void setCourse(SelectOption course) {
        this.course = course;
    }
    
    public SelectOption getClass_room() {
        return class_room;
    }

    public void setClass_room(SelectOption class_room) {
        this.class_room = class_room;
    }
    
    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public Integer getCurrentStudentCount() {
        return currentStudentCount;
    }

    public void setCurrentStudentCount(Integer currentStudentCount) {
        this.currentStudentCount = currentStudentCount;
    }

    public Integer getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(Integer teacherID) {
        this.teacherID = teacherID;
    }

    public Integer getClassID() {
        return classID;
    }

    public void setClassID(Integer classID) {
        this.classID = classID;
    }

    public Time getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Time beginTime) {
        this.beginTime = beginTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public SelectOption getDaySelect() {
        return daySelect;
    }

    public void setDaySelect(SelectOption daySelect) {
        this.daySelect = daySelect;
    }

    public String getBeginTimeInString() {
        return beginTimeInString;
    }

    public void setBeginTimeInString(String beginTimeInString) {
        this.beginTimeInString = beginTimeInString;
    }

    public String getEndTimeInString() {
        return endTimeInString;
    }

    public void setEndTimeInString(String endTimeInString) {
        this.endTimeInString = endTimeInString;
    }

}
