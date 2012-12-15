/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation.model;

/**
 *
 * @author Oguzzo
 */
public class Course {

    private int id;
    private String name;
    private int code;
    private int credits;
    private int facultyID;
    private int length;
    
    public Course() {
    }

    public Course(int id, String name, int code, int credits, int facultyID, int length) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.credits = credits;
        this.facultyID = facultyID;
        this.length = length;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(int facultyID) {
        this.facultyID = facultyID;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    
    
}
