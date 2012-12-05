package itucs.blg361e.courseautomation.model;

/**
 *
 * @author Berker
 */
public class Student extends User{
    private Integer number;
    private Integer studentID = null; // corresponds to the id in the student table (not student no)
    
    public Student(String nName, String nUsername, String nPassword, Integer nNumber) {
        super(nName, nUsername, nPassword);
        number = nNumber;
    }

    public void setNumber(Integer newNumber) {
        this.number = newNumber;
    }

    public Integer getNumber() {
        return this.number;
    }
    
    public void setStudentID(Integer newStudentID) {
        this.studentID = newStudentID;
    }

    public Integer getStudentID() {
        return this.studentID;
    }
}
