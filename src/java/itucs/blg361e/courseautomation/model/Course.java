package itucs.blg361e.courseautomation.model;

import itucs.blg361e.courseautomation.utility.SelectOption;
import java.util.List;

/**
 *
 * @author Berker
 */
public class Course{

    private Integer id = null;
    private String name;
    private String code;
    private Integer credits;
    private Integer facultyID;
    private Integer length; 
    
    private SelectOption faculty;
    
    public Course() {
    }
    
    public Course(String name, String code, Integer credits, Integer facultyID, Integer length) {
        this.name = name;
        this.code = code;
        this.credits = credits;
        this.facultyID = facultyID;
        this.length = length;
    }
    
    public SelectOption getFaculty() {
        return faculty;
    }

    public void setFaculty(SelectOption faculty) {
        this.faculty = faculty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setId(SelectOption selection) {
        this.id = Integer.parseInt(selection.getValue());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Integer getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(Integer facultyID) {
        this.facultyID = facultyID;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
    
}
