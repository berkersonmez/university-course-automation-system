/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation.model;

/**
 *
 * @author Oguzzo
 */
public class StudentCourse {
    private Integer id = null;
    private Integer userID;
    private Integer CRN;
    
    public StudentCourse(){
        
    };
    
    public StudentCourse(Integer scUserID, Integer scCRN) {
        this.userID = scUserID;
        this.CRN = scCRN;
    }
    
    public StudentCourse(Integer ID, Integer scUserID, Integer scCRN) {
        this.userID = scUserID;
        this.CRN = scCRN;
        this.id = ID;
    }
    
    public void setUserID(int value){
        this.userID = value;
    }
    
    public void setCRN(int value){
        this.CRN = value;
    }
    
    public Integer getUserID(){
        return userID;
    }
    
    public Integer getCRN(){
        return CRN;
    }
}
