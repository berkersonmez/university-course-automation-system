package itucs.blg361e.courseautomation.model;

/**
 *
 * @author Berker
 */
public class Teacher extends User{
    private Integer teacherID = null; // corresponds to the id in the teacher table (not student no)
    
    public Teacher(){
        super();
    }
    
    public Teacher(String nName, String nUsername, String nPassword) {
        super(nName, nUsername, nPassword);
    }
    
    public void setTeacherID(Integer newTeacherID) {
        this.teacherID = newTeacherID;
    }

    public Integer getTeacherID() {
        return this.teacherID;
    }
}
