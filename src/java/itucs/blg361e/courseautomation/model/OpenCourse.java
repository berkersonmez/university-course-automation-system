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
    private Integer class_roomID;
    private Time beginTime;
    private Time endTime;
    private String day = null;
    private SelectOption course;
    private SelectOption class_room;

    

    
    
    public OpenCourse(){
        
    };
    
    public OpenCourse(Integer CRN, Integer courseID, Integer quota, Integer currentStudentCount, Integer teacherID, Integer classID, Time beginTime, Time endTime) {
        this.CRN = CRN;
        this.courseID = courseID;
        this.quota = quota;
        this.currentStudentCount = currentStudentCount;
        this.teacherID = teacherID;
        this.class_roomID = classID;
        this.beginTime = beginTime;
        this.endTime = endTime;
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

    public Integer getClass_roomID() {
        return class_roomID;
    }

    public void setClass_roomID(Integer classID) {
        this.class_roomID = classID;
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

}
