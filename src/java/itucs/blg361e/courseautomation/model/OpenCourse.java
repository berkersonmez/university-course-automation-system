/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation.model;

/**
 *
 * @author Oguzzo
 */
public class OpenCourse {    

    
    private Integer CRN;
    private Integer courseID;
    private Integer quota;
    private Integer currentStudentCount;
    private Integer teacherID;
    private Integer classID;
    private String beginTime;
    private String endTime;

    public OpenCourse(){
        
    };
    
    public OpenCourse(Integer CRN, Integer courseID, Integer quota, Integer currentStudentCount, Integer teacherID, Integer classID, String beginTime, String endTime) {
        this.CRN = CRN;
        this.courseID = courseID;
        this.quota = quota;
        this.currentStudentCount = currentStudentCount;
        this.teacherID = teacherID;
        this.classID = classID;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }
    
    public Integer getCRN() {
        return CRN;
    }

    public void setCRN(Integer CRN) {
        this.CRN = CRN;
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

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

}
