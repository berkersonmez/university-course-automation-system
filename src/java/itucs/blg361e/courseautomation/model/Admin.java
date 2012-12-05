package itucs.blg361e.courseautomation.model;

/**
 *
 * @author Berker
 */
public class Admin extends User{
    private Integer adminID = null; // corresponds to the id in the admin table (not student no)
    
    public Admin(String nName, String nUsername, String nPassword) {
        super(nName, nUsername, nPassword);
    }
    
    public void setAdminID(Integer newAdminID) {
        this.adminID = newAdminID;
    }

    public Integer getAdminID() {
        return this.adminID;
    }
}
